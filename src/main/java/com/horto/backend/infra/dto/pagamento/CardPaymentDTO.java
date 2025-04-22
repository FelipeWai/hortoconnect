package com.horto.backend.infra.dto.pagamento;

public record CardPaymentDTO(
        String token,
        String paymentMethodId,
        Long planId,
        Integer installments,
        String description,
        PayerDTO payer
) {
}
