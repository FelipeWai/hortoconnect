package com.horto.backend.infra.dto.subcategory.response;

import com.horto.backend.infra.dto.category.response.CategoryResponseDTO;
import com.horto.backend.infra.dto.quality.response.QualityResponseDTO;
import com.horto.backend.infra.dto.size.response.SizeResponseDTO;

import java.util.List;

public record SubcategoryResponseDTO(
        Long id,
        String name,
        CategoryResponseDTO category,
        List<QualityResponseDTO> qualities,
        List<SizeResponseDTO> sizes
) {
}
