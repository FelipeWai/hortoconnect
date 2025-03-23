package com.horto.backend.infra.mapper;

import com.horto.backend.core.entities.Product;
import com.horto.backend.infra.dto.product.request.ProductRequestDTO;
import com.horto.backend.infra.dto.product.response.ProductResponseDTO;
import com.horto.backend.infra.persistence.entities.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ProductPictureMapper.class)
public interface ProductMapper {

    @Mapping(target = "pictures", qualifiedByName = "mapWithoutProduct")
    Product toDomain(ProductEntity entity);

    ProductResponseDTO toResponseDTO(Product product);

    ProductEntity toEntity(Product product);
    ProductEntity toEntity(ProductRequestDTO productRequestDTO);


}
