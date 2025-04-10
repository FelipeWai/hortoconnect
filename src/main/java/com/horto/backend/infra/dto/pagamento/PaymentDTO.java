package com.horto.backend.infra.dto.pagamento;

public record PaymentDTO(
        String token,
        Long planId,
        String payment_method,
        String userEmail
) {
}
