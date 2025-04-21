package com.horto.backend.infra.dto.pagamento;

import jakarta.validation.constraints.NotNull;

public record PayerDTO(
        @NotNull
        String email,
        @NotNull
        PayerIdentificationDTO identification
) {
}
