package com.horto.backend.core.entities;

public record ProductPicture(
        Long id,
        String url,
        Product product
) {
}
