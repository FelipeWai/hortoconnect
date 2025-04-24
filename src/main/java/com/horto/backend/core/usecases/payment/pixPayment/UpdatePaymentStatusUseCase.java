package com.horto.backend.core.usecases.payment.pixPayment;

public interface UpdatePaymentStatusUseCase {
    void execute(Long externalPaymentId);
}
