package com.horto.backend.core.entities;

import java.time.LocalDateTime;

public record Size(
        Long id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
