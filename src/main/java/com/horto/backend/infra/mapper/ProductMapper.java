package com.horto.backend.infra.mapper;

import com.horto.backend.core.entities.Product;
import com.horto.backend.infra.dto.product.request.ProductRequestDTO;
import com.horto.backend.infra.dto.product.response.ProductResponseDTO;
import com.horto.backend.infra.persistence.entities.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toDomain(ProductEntity entity);

    ProductResponseDTO toResponseDTO(Product product);

    ProductEntity toEntity(Product product);
    ProductEntity toEntity(ProductRequestDTO productRequestDTO);


}
