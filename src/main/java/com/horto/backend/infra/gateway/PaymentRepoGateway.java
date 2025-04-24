package com.horto.backend.infra.gateway;

import com.horto.backend.core.entities.Payment;
import com.horto.backend.core.gateway.PaymentGateway;
import com.horto.backend.infra.dto.pagamento.PixPaymentDTO;
import com.horto.backend.infra.mapper.PaymentMapper;
import com.horto.backend.infra.persistence.entities.PaymentEntity;
import com.horto.backend.infra.persistence.repositories.PaymentRepository;
import com.horto.backend.infra.service.MercadoPagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentRepoGateway implements PaymentGateway {

    private final PaymentRepository paymentRepository;

    private final PaymentMapper paymentMapper;

    private final MercadoPagoService mercadoPagoService;

    @Override
    public Payment processPixPayment(PixPaymentDTO pixPaymentDTO) {
        PaymentEntity entity = mercadoPagoService.createPixPayment(pixPaymentDTO);
        PaymentEntity savedEntity = paymentRepository.save(entity);
        savedEntity.setQrCodeBase64(entity.getQrCodeBase64());

        return paymentMapper.toDomain(savedEntity);
    }

    @Override
    public void updatePaymentStatus(Long externalPaymentId) {
        PaymentEntity entity = mercadoPagoService.consultPayment(externalPaymentId);
        paymentRepository.save(entity);
    }

}
