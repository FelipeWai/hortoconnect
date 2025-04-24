package com.horto.backend.core.gateway;

import com.horto.backend.core.entities.Payment;
import com.horto.backend.infra.dto.pagamento.PixPaymentDTO;

public interface PaymentGateway {
    Payment processPixPayment(PixPaymentDTO pixPaymentDTO);
    void updatePaymentStatus(Long externalPaymentId);
}
