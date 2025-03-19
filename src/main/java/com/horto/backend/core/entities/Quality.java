package com.horto.backend.core.entities;

import java.time.LocalDateTime;

public record Quality(
        Long id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
