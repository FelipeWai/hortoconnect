package com.horto.backend.core.entities;

import java.time.LocalDateTime;
import java.util.List;

public record Subcategory(
        Long id,
        String name,
        Category category,
        List<Quality> qualities,
        List<Size> sizes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
