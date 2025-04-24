package com.horto.backend.core.entities;

import com.horto.backend.core.enums.SubscriptionStatus;

import java.time.LocalDateTime;

public record Subscription(
        Long id,
        User user,
        Long planId,
        SubscriptionStatus status,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Payment lastPayment,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}