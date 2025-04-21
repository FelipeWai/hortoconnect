package com.horto.backend.infra.dto.pagamento;

public record PixPaymentRespondeDTO(
        Long id,
        String status,
        String status_detail,
        String qrCodeBase64,
        String qrCode
) {
}
