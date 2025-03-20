package com.horto.backend.core.entities;

import java.time.LocalDateTime;

public record Product(
        Long id,
        String name,
        Subcategory subcategory,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
