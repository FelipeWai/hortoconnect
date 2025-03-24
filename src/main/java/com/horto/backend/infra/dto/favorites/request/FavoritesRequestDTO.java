package com.horto.backend.infra.dto.favorites.request;

public record FavoritesRequestDTO(
        Long user_id,
        Long product_id
) {
}
