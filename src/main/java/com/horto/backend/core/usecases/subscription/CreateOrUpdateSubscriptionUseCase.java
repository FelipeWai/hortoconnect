package com.horto.backend.core.usecases.subscription;

import com.horto.backend.core.entities.Subscription;

public interface CreateOrUpdateSubscriptionUseCase {

    void execute(Long userId,  Long planId);

}
