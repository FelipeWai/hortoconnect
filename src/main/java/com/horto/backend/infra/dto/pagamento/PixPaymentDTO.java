package com.horto.backend.infra.dto.pagamento;

public record PixPaymentDTO(
        Long planId,
        String description,
        String paymentMethodId,
        PayerDTO payer
) {
}
