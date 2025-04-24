package com.horto.backend.core.gateway;

import com.horto.backend.core.entities.Subscription;

import java.util.Optional;

public interface SubscriptionGateway {

    void createOrUpdateSubscription(Long userId, Long planId);

    Optional<Subscription> getSubscriptionByUserId(Long userId);

}
