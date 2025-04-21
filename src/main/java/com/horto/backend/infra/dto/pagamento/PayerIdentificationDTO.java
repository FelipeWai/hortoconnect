package com.horto.backend.infra.dto.pagamento;

public record PayerIdentificationDTO(
        String type,
        String number
) {
}
