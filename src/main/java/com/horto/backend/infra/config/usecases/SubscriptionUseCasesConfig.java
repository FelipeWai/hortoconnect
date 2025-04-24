package com.horto.backend.infra.config.usecases;

import com.horto.backend.core.usecases.subscription.CreateOrUpdateSubscriptionUseCase;
import com.horto.backend.core.usecases.subscription.CreateOrUpdateSubscriptionUseCaseImpl;
import com.horto.backend.core.usecases.subscription.GetSubscriptionByUserIdUseCase;
import com.horto.backend.core.usecases.subscription.GetSubscriptionByUserIdUseCaseImpl;
import com.horto.backend.infra.gateway.SubscriptionRepoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SubscriptionUseCasesConfig {

    private final SubscriptionRepoGateway subscriptionRepoGateway;

    @Bean
    public CreateOrUpdateSubscriptionUseCase createSubscriptionUseCase() {
        return new CreateOrUpdateSubscriptionUseCaseImpl(subscriptionRepoGateway);
    }

    @Bean
    public GetSubscriptionByUserIdUseCase getSubscriptionByUserIdUseCase() {
        return new GetSubscriptionByUserIdUseCaseImpl(subscriptionRepoGateway);
    }

}
