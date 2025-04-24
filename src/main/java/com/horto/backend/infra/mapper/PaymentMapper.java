package com.horto.backend.infra.mapper;

import com.horto.backend.core.entities.Payment;
import com.horto.backend.infra.dto.pagamento.PixPaymentRespondeDTO;
import com.horto.backend.infra.persistence.entities.PaymentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment toDomain(PaymentEntity entity);

    PaymentEntity toEntity(Payment domain);

    PixPaymentRespondeDTO toPixResponseDTO(Payment domain);

}
