package com.horto.backend.infra.dto.pagamento;

public record CardPaymentResponseDTO(
        Long id,
        String status,
        String detail
) {}