package com.horto.backend.core.entities;

import java.time.LocalDateTime;

public record Favorites(
        Long id,
        Product product,
        User user,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
