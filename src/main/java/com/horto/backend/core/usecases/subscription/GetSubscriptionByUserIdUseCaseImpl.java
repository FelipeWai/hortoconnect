package com.horto.backend.core.usecases.subscription;

import com.horto.backend.core.entities.Subscription;
import com.horto.backend.core.exceptions.subscription.InvalidSubscriptionException;
import com.horto.backend.core.gateway.SubscriptionGateway;

public class GetSubscriptionByUserIdUseCaseImpl implements GetSubscriptionByUserIdUseCase {
    private final SubscriptionGateway subscriptionGateway;

    public GetSubscriptionByUserIdUseCaseImpl(SubscriptionGateway subscriptionGateway) {
        this.subscriptionGateway = subscriptionGateway;
    }

    @Override
    public Subscription execute(Long userId) {
        return subscriptionGateway.getSubscriptionByUserId(userId)
                .orElseThrow(() -> new InvalidSubscriptionException("Subscription not found"));
    }
}
