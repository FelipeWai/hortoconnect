package com.horto.backend.infra.mapper;

import com.horto.backend.core.entities.Subcategory;
import com.horto.backend.infra.dto.subcategory.request.SubcategoryRequestDTO;
import com.horto.backend.infra.dto.subcategory.response.SubcategoryResponseDTO;
import com.horto.backend.infra.persistence.entities.SubcategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubcategoryMapper {

    Subcategory toDomain(SubcategoryEntity entity);

    SubcategoryEntity toEntity(Subcategory subcategory);
    SubcategoryEntity toEntity(SubcategoryRequestDTO subcategory);


    SubcategoryResponseDTO toResponseDTO(Subcategory entity);

}
