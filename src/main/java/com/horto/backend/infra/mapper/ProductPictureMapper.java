package com.horto.backend.infra.mapper;

import com.horto.backend.core.entities.ProductPicture;
import com.horto.backend.infra.dto.productPicture.request.ProductPictureRequestDTO;
import com.horto.backend.infra.dto.productPicture.response.ProductPictureResponseDTO;
import com.horto.backend.infra.persistence.entities.ProductPictureEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ProductPictureMapper {

    @Mapping(target = "product", ignore = true)
    ProductPicture toDomain(ProductPictureEntity entity);

    ProductPictureResponseDTO toResponseDTO(ProductPicture productPicture);

    @Mapping(target = "product", source = "product")
    ProductPictureEntity toEntity(ProductPicture productPicture);

    ProductPictureEntity toEntity(ProductPictureRequestDTO requestDTO);

}
