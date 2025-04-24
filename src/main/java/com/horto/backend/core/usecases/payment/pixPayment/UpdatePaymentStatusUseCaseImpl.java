package com.horto.backend.core.usecases.payment.pixPayment;

import com.horto.backend.core.gateway.PaymentGateway;

public class UpdatePaymentStatusUseCaseImpl implements UpdatePaymentStatusUseCase {

    private final PaymentGateway paymentGateway;

    public UpdatePaymentStatusUseCaseImpl(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Override
    public void execute(Long externalPaymentId) {
        paymentGateway.updatePaymentStatus(externalPaymentId);
    }

}
