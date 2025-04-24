package com.horto.backend.core.usecases.subscription;

import com.horto.backend.core.entities.Subscription;

public interface GetSubscriptionByUserIdUseCase {
    Subscription execute(Long userId);
}
