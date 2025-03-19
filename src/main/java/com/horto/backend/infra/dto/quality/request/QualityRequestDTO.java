package com.horto.backend.infra.dto.quality.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record QualityRequestDTO(
        @NotNull
        @Size(min = 1, max = 50)
        String name
) {
}
