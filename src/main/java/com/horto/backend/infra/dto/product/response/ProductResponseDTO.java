package com.horto.backend.infra.dto.product.response;

import com.horto.backend.infra.dto.productPicture.response.ProductPictureResponseDTO;
import com.horto.backend.infra.dto.subcategory.response.SubcategoryResponseDTO;

import java.util.List;

public record ProductResponseDTO(
        Long id,
        String name,
        SubcategoryResponseDTO subcategory,
        List<ProductPictureResponseDTO> pictures
) {
}
