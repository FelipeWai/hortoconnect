package com.horto.backend.infra.gateway;

import com.horto.backend.core.entities.*;
import com.horto.backend.core.exceptions.supplierOffer.OffersNotFoundException;
import com.horto.backend.core.exceptions.supplierOffer.OneOfferNotFoundException;
import com.horto.backend.core.gateway.SupplierOfferGateway;
import com.horto.backend.core.usecases.product.get.GetProductByIdCase;
import com.horto.backend.core.usecases.quality.get.GetQualityByIdCase;
import com.horto.backend.core.usecases.size.get.GetSizeByIdCase;
import com.horto.backend.core.usecases.suppliers.get.GetSupplierByIdCase;
import com.horto.backend.infra.dto.product.response.ProductNameResponseDTO;
import com.horto.backend.infra.dto.supplier.response.SupplierNameDTO;
import com.horto.backend.infra.dto.supplierOffer.request.SupplierOfferPatchDTO;
import com.horto.backend.infra.dto.supplierOffer.request.SupplierOfferRequestDTO;
import com.horto.backend.infra.dto.supplierOffer.response.PriceRangeDTO;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOfferResponseDTO;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOffersGroupedResponseDTO;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOffersSummaryDTO;
import com.horto.backend.infra.filters.offer.OfferFilter;
import com.horto.backend.infra.filters.offer.OfferSpecification;
import com.horto.backend.infra.mapper.*;
import com.horto.backend.infra.persistence.entities.SupplierEntity;
import com.horto.backend.infra.persistence.entities.SupplierOfferEntity;
import com.horto.backend.infra.persistence.repositories.SupplierOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SupplierOfferRepoGateway implements SupplierOfferGateway {

    private final SupplierOfferRepository supplierOfferRepository;

    private final GetSupplierByIdCase getSupplierByIdCase;
    private final GetQualityByIdCase getQualityByIdCase;
    private final GetSizeByIdCase getSizeByIdCase;
    private final GetProductByIdCase getProductByIdCase;

    private final SupplierOfferMapper supplierOfferMapper;
    private final ProductMapper productMapper;
    private final SupplierMapper supplierMapper;
    private final SizeMapper sizeMapper;
    private final QualityMapper qualityMapper;

    @Override
    public List<SupplierOffersGroupedResponseDTO<SupplierOfferResponseDTO>> getOffersBySupplierId(Long id) {
        getSupplierByIdCase.execute(id);

        List<SupplierOfferEntity> entityList = supplierOfferRepository.findAllBySupplier_Id(id);

        Map<ProductNameResponseDTO, List<SupplierOfferResponseDTO>> grouped = entityList.stream()
                .collect(Collectors.groupingBy(
                        entity -> productMapper.toNameResponseDTO(productMapper.toDomain(entity.getProduct())),
                        Collectors.mapping(supplierOfferMapper::toResponseDTO, Collectors.toList())
                ));

        return grouped.entrySet().stream()
                .map(entry -> new SupplierOffersGroupedResponseDTO<>(entry.getKey(), entry.getValue()))
                .toList();
    }

    @Override
    public SupplierOffer createOffer(SupplierOfferRequestDTO requestDTO) {
        Supplier supplier = getSupplierByIdCase.execute(requestDTO.supplier_id());
        Quality quality = getQualityByIdCase.execute(requestDTO.quality_id());
        Size size = getSizeByIdCase.execute(requestDTO.size_id());
        Product product = getProductByIdCase.execute(requestDTO.product_id());

        Set<Size> productSizes = new HashSet<>(product.subcategory().sizes());
        Set<Quality> productQualities = new HashSet<>(product.subcategory().qualities());
        if(!productSizes.contains(size) || !productQualities.contains(quality)) {
            throw new IllegalArgumentException("Tamanho ou qualidade não disponível para o produto selecionado");
        }

        Optional<SupplierOfferEntity> existingOffer = supplierOfferRepository.findBySupplierAndProductAndSizeAndQualityIds(
                supplier.id(),
                product.id(),
                size.id(),
                quality.id()
        );

        if(existingOffer.isPresent()) {
            throw new IllegalStateException("Já existe uma oferta deste fornecedor para este produto com o mesmo tamanho e qualidade");
        }

        SupplierOfferEntity supplierOfferEntity = supplierOfferMapper.toEntity(requestDTO);
        supplierOfferEntity.setProduct(productMapper.toEntity(product));
        supplierOfferEntity.setSize(sizeMapper.toEntity(size));
        supplierOfferEntity.setQuality(qualityMapper.toEntity(quality));
        supplierOfferEntity.setSupplier(supplierMapper.toEntity(supplier));

        SupplierOfferEntity saved = supplierOfferRepository.save(supplierOfferEntity);

        return supplierOfferMapper.toDomain(saved);
    }

    @Override
    public List<SupplierOffersSummaryDTO> getOffersByProductId(Long productId, OfferFilter filter) {
        getProductByIdCase.execute(productId);

        OfferSpecification spec = new OfferSpecification(productId, filter);
        List<SupplierOfferEntity> offersList = supplierOfferRepository.findAll(spec, Sort.by("minPrice").ascending());
        if (offersList.isEmpty()) {
            throw new OffersNotFoundException(productId.toString());
        }

        Map<Long, List<SupplierOfferEntity>> offersBySupplierId = offersList.stream()
                .collect(Collectors.groupingBy(offer -> offer.getSupplier().getId()));

        return offersBySupplierId.entrySet().stream()
                .map(entry -> {
                    Long supplierId = entry.getKey();
                    List<SupplierOfferEntity> supplierOffers = entry.getValue();

                    // Obter o primeiro para extrair informações do fornecedor
                    SupplierOfferEntity firstOffer = supplierOffers.get(0);
                    SupplierEntity supplier = firstOffer.getSupplier();

                    // Extrair qualidades únicas
                    List<String> qualities = supplierOffers.stream()
                            .map(offer -> offer.getQuality().getName())
                            .distinct()
                            .collect(Collectors.toList());

                    // Extrair tamanhos únicos
                    List<String> sizes = supplierOffers.stream()
                            .map(offer -> offer.getSize().getName())
                            .distinct()
                            .collect(Collectors.toList());

                    // Encontrar o menor preço mínimo e o maior preço máximo
                    Double minPrice = supplierOffers.stream()
                            .map(SupplierOfferEntity::getMinPrice)
                            .min(Double::compare)
                            .orElse(0.0);

                    Double maxPrice = supplierOffers.stream()
                            .map(SupplierOfferEntity::getMaxPrice)
                            .max(Double::compare)
                            .orElse(0.0);

                    // Criar o DTO do fornecedor
                    return new SupplierOffersSummaryDTO(
                            new SupplierNameDTO(
                                    supplier.getId(),
                                    supplier.getName()
                            ),
                            qualities,
                            sizes,
                            new PriceRangeDTO(minPrice, maxPrice)
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public SupplierOffersGroupedResponseDTO getOffersByProductAndSupplierId(Long productId, Long supplierId) {
        Product product = getProductByIdCase.execute(productId);
        List<SupplierOfferEntity> offersList = supplierOfferRepository.findAllByProduct_IdAndSupplier_Id(productId, supplierId);
        return new SupplierOffersGroupedResponseDTO(
                productMapper.toNameResponseDTO(product),
                offersList.stream()
                        .map(supplierOfferMapper::toResponseDTO)
                        .toList()
        );
    }

    @Override
    public void deleteOfferById(Long id) {
        if(getOfferById(id).isEmpty()){
            throw new OneOfferNotFoundException(id.toString());
        }
        supplierOfferRepository.deleteById(id);
    }

    @Override
    public Optional<SupplierOffer> getOfferById(Long id) {
        Optional<SupplierOfferEntity> offer = supplierOfferRepository.findById(id);
        return offer.map(supplierOfferMapper::toDomain);
    }

    @Override
    public SupplierOffer patchOfferById(Long id, SupplierOfferPatchDTO patchDTO) {
        SupplierOfferEntity entityToUpdate = supplierOfferRepository.findById(id)
                .orElseThrow(() -> new OneOfferNotFoundException(id.toString()));

        patchDTO.minPrice().ifPresent(minPrice -> {
            if (!minPrice.equals(entityToUpdate.getMinPrice())) {
                entityToUpdate.setMinPrice(minPrice);
            }
        });

        patchDTO.maxPrice().ifPresent(maxPrice -> {
            if (!maxPrice.equals(entityToUpdate.getMaxPrice())) {
                entityToUpdate.setMaxPrice(maxPrice);
            }
        });

        if (entityToUpdate.getMinPrice() != null && entityToUpdate.getMaxPrice() != null) {
            if (entityToUpdate.getMinPrice() > entityToUpdate.getMaxPrice()) {
                throw new IllegalArgumentException("O preço mínimo não pode ser maior que o preço máximo");
            }
        }

        SupplierOfferEntity updatedEntity = supplierOfferRepository.save(entityToUpdate);

        return supplierOfferMapper.toDomain(updatedEntity);
    }
}
