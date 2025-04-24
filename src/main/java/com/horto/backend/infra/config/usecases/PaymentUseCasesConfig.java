package com.horto.backend.infra.config.usecases;

import com.horto.backend.core.usecases.payment.pixPayment.ProcessPixPaymentUseCase;
import com.horto.backend.core.usecases.payment.pixPayment.ProcessPixPaymentUseCaseImpl;
import com.horto.backend.core.usecases.payment.pixPayment.UpdatePaymentStatusUseCase;
import com.horto.backend.core.usecases.payment.pixPayment.UpdatePaymentStatusUseCaseImpl;
import com.horto.backend.infra.gateway.PaymentRepoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PaymentUseCasesConfig {

    private final PaymentRepoGateway paymentRepoGateway;

    @Bean
    public ProcessPixPaymentUseCase processPixPaymentUseCase() {
        return new ProcessPixPaymentUseCaseImpl(paymentRepoGateway);
    }

    @Bean
    public UpdatePaymentStatusUseCase updatePaymentStatusUseCase() {
        return new UpdatePaymentStatusUseCaseImpl(paymentRepoGateway);
    }

}
