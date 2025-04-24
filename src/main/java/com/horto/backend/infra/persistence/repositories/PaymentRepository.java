package com.horto.backend.infra.persistence.repositories;

import com.horto.backend.infra.persistence.entities.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {
    Optional<PaymentEntity> findByExternalPaymentId(Long externalPaymentId);

    List<PaymentEntity> findByUser_IdOrderByPaymentDateDesc(Long userId);
}
