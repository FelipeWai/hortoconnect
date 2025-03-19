package com.horto.backend.core.entities;

import java.time.LocalDateTime;

public record Category(
        Long id,
        String nome,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
