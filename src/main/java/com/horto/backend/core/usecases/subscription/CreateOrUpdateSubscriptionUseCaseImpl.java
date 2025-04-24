package com.horto.backend.core.usecases.subscription;

import com.horto.backend.core.entities.Subscription;
import com.horto.backend.core.gateway.SubscriptionGateway;

public class CreateOrUpdateSubscriptionUseCaseImpl implements CreateOrUpdateSubscriptionUseCase {
    private final SubscriptionGateway subscriptionGateway;

    public CreateOrUpdateSubscriptionUseCaseImpl(SubscriptionGateway subscriptionGateway) {
        this.subscriptionGateway = subscriptionGateway;
    }

    @Override
    public void execute(Long userId, Long planId) {
        subscriptionGateway.createOrUpdateSubscription(userId, planId);
    }
}
