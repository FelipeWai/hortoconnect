package com.horto.backend.infra.dto.size.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SizeRequestDTO(
        @NotNull
        @Size(min = 1, max = 50)
        String name
) {
}
