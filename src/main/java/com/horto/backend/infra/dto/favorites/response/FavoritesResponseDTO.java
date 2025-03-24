package com.horto.backend.infra.dto.favorites.response;

import com.horto.backend.infra.dto.product.response.ProductResponseDTO;

public record FavoritesResponseDTO(
        Long id,
        ProductResponseDTO product
) {
}
