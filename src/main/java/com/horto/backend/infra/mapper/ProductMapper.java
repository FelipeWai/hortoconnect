package com.horto.backend.infra.mapper;

import com.horto.backend.core.entities.Product;
import com.horto.backend.core.entities.ProductPicture;
import com.horto.backend.infra.dto.product.request.ProductRequestDTO;
import com.horto.backend.infra.dto.product.response.ProductNameResponseDTO;
import com.horto.backend.infra.dto.product.response.ProductResponseDTO;
import com.horto.backend.infra.persistence.entities.ProductEntity;
import com.horto.backend.infra.persistence.entities.ProductPictureEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    // Mapeamento de fotos para evitar recursão infinta
    @Mapping(target = "product", ignore = true)
    ProductPicture toDomain(ProductPictureEntity entity);

    Product toDomain(ProductEntity entity);

    ProductResponseDTO toResponseDTO(Product product);

    ProductNameResponseDTO toNameResponseDTO(Product product);

    ProductEntity toEntity(Product product);
    ProductEntity toEntity(ProductRequestDTO productRequestDTO);
}
