package com.horto.backend.infra.dto.product.response;

import com.horto.backend.infra.dto.subcategory.response.SubcategoryResponseDTO;

public record ProductResponseDTO(
        Long id,
        String name,
        SubcategoryResponseDTO subcategory
) {
}
