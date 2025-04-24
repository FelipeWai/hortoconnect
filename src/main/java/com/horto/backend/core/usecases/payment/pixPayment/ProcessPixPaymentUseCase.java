package com.horto.backend.core.usecases.payment.pixPayment;

import com.horto.backend.core.entities.Payment;
import com.horto.backend.infra.dto.pagamento.PixPaymentDTO;

public interface ProcessPixPaymentUseCase {
    Payment execute(PixPaymentDTO pixPaymentDTO);
}
