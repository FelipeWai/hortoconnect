package com.horto.backend.infra.dto.category.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoryRequestDTO(
        @NotNull
        @Size(min = 1, max = 100)
        String name
) {}
