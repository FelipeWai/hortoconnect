package com.horto.backend.infra.gateway;

import com.horto.backend.core.entities.Product;
import com.horto.backend.core.entities.Subcategory;
import com.horto.backend.core.exceptions.product.ProductAlreadyExists;
import com.horto.backend.core.exceptions.product.ProductNotFoundException;
import com.horto.backend.core.exceptions.subcategory.SubcategoryNotFoundException;
import com.horto.backend.core.gateway.ProductGateway;
import com.horto.backend.core.usecases.subcategory.get.GetSubcategoryByIdCase;
import com.horto.backend.infra.dto.product.request.ProductPatchDTO;
import com.horto.backend.infra.dto.product.request.ProductRequestDTO;
import com.horto.backend.infra.mapper.ProductMapper;
import com.horto.backend.infra.mapper.SubcategoryMapper;
import com.horto.backend.infra.persistence.entities.ProductEntity;
import com.horto.backend.infra.persistence.entities.SubcategoryEntity;
import com.horto.backend.infra.persistence.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductRepoGateway implements ProductGateway {

    private final GetSubcategoryByIdCase getSubcategoryByIdCase;

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;
    private final SubcategoryMapper subcategoryMapper;


    @Override
    public List<Product> getAllProducts() {
        List<ProductEntity> entityList = productRepository.findAll();
        return entityList.stream()
                .map(productMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        Optional<ProductEntity> entityOptional = productRepository.findById(id);
        if (entityOptional.isPresent()) {
            ProductEntity productEntity = entityOptional.get();
            return Optional.of(productMapper.toDomain(productEntity));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        String nameLowerCase = name.trim().toLowerCase();
        Optional<ProductEntity> product = productRepository.findByName(nameLowerCase);
        if (product.isPresent()) {
            ProductEntity productEntity = product.get();
            return Optional.of(productMapper.toDomain(productEntity));
        }
        return Optional.empty();
    }

    @Override
    public Product createProduct(ProductRequestDTO requestDTO) {
        String nameLowerCase = requestDTO.name().trim().toLowerCase();
        if(getProductByName(nameLowerCase).isPresent()) {
            throw new ProductAlreadyExists(nameLowerCase);
        }
        Subcategory subcategory = getSubcategoryByIdCase.execute(requestDTO.subcategory_id());
        ProductEntity productEntity = productMapper.toEntity(requestDTO);
        productEntity.setName(nameLowerCase);
        productEntity.setSubcategory(subcategoryMapper.toEntity(subcategory));
        ProductEntity savedEntity = productRepository.save(productEntity);
        return productMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteProductById(Long id) {
        if(getProductById(id).isPresent()) {
            productRepository.deleteById(id);
        }else{
            throw new ProductNotFoundException(id.toString());
        }
    }

    @Override
    public Product patchProductById(Long id, ProductPatchDTO patchDTO) {
        Product productToUpdate = getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));

        ProductEntity productEntity = productMapper.toEntity(productToUpdate);

        if(patchDTO.name().isPresent()){
            if(!patchDTO.name().get().equals(productEntity.getName())){
                if(getProductByName(patchDTO.name().get()).isPresent()) {
                    throw new ProductAlreadyExists(patchDTO.name().get());
                }
                productEntity.setName(patchDTO.name().get());
            }else{
                throw new ProductAlreadyExists(patchDTO.name().get());
            }
        }

        if(patchDTO.subcategory_id().isPresent()) {
            if(!patchDTO.subcategory_id().get().equals(productEntity.getId())){
                SubcategoryEntity subcategoryEntity = subcategoryMapper.toEntity(getSubcategoryByIdCase.execute(patchDTO.subcategory_id().get()));
                productEntity.setSubcategory(subcategoryEntity);
            }
        }

        ProductEntity savedEntity = productRepository.save(productEntity);
        return productMapper.toDomain(savedEntity);
    }
}
