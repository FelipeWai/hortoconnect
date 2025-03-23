package com.horto.backend.core.entities;

import java.time.LocalDateTime;
import java.util.List;

public record Product(
        Long id,
        String name,
        Subcategory subcategory,
        List<ProductPicture> pictures,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
