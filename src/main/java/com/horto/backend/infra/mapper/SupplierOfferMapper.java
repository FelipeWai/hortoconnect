package com.horto.backend.infra.mapper;

import com.horto.backend.core.entities.Product;
import com.horto.backend.core.entities.ProductPicture;
import com.horto.backend.core.entities.SupplierOffer;
import com.horto.backend.infra.dto.product.response.ProductNameResponseDTO;
import com.horto.backend.infra.dto.supplierOffer.request.SupplierOfferRequestDTO;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOfferResponseDTO;
import com.horto.backend.infra.persistence.entities.ProductPictureEntity;
import com.horto.backend.infra.persistence.entities.SupplierOfferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SupplierOfferMapper {

    // Mapeamento de fotos para evitar recursão infinta
    @Mapping(target = "product", ignore = true)
    ProductPicture toDomain(ProductPictureEntity entity);

    SupplierOffer toDomain(SupplierOfferEntity entity);

    SupplierOfferEntity toEntity(SupplierOffer domain);
    SupplierOfferEntity toEntity(SupplierOfferRequestDTO request);

    SupplierOfferResponseDTO toResponseDTO(SupplierOffer domain);
    SupplierOfferResponseDTO toResponseDTO(SupplierOfferEntity entity);
}
