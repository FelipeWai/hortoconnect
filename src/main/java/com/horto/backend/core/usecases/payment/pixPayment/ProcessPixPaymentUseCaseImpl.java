package com.horto.backend.core.usecases.payment.pixPayment;

import com.horto.backend.core.entities.Payment;
import com.horto.backend.core.exceptions.payment.InvalidPaymentException;
import com.horto.backend.core.gateway.PaymentGateway;
import com.horto.backend.infra.dto.pagamento.PixPaymentDTO;

public class ProcessPixPaymentUseCaseImpl implements ProcessPixPaymentUseCase {

    private final PaymentGateway paymentGateway;

    public ProcessPixPaymentUseCaseImpl(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Override
    public Payment execute(PixPaymentDTO pixPaymentDTO) {
        if (pixPaymentDTO.planId() <= 0) {
            throw new InvalidPaymentException("Plano inválido");
        }

        if (pixPaymentDTO.description() == null || pixPaymentDTO.description().trim().isEmpty()) {
            throw new InvalidPaymentException("Descrição do pagamento não pode ser vazia");
        }
        return paymentGateway.processPixPayment(pixPaymentDTO);
    }
}
