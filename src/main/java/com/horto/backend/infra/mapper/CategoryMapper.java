package com.horto.backend.infra.mapper;

import com.horto.backend.core.entities.Category;
import com.horto.backend.infra.dto.category.request.CategoryRequestDTO;
import com.horto.backend.infra.dto.category.response.CategoryResponseDTO;
import com.horto.backend.infra.persistence.entities.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toDomain(CategoryEntity entity);

    CategoryResponseDTO toResponseDTO(Category category);

    CategoryEntity toEntity(CategoryRequestDTO categoryRequestDTO);

}
