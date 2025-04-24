package com.horto.backend.core.entities;

import com.horto.backend.core.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Payment(
        Long id,
        User user,
        Long externalPaymentId,
        BigDecimal amount,
        Long planId,
        String paymentMethod,
        PaymentStatus status,
        String statusDetail,
        LocalDateTime paymentDate,
        LocalDateTime expirationDate,
        String qrCodeBase64,
        String qrCode,
        String transactionId,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}