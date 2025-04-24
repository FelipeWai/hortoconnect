package com.horto.backend.infra.persistence.repositories;

import com.horto.backend.core.enums.SubscriptionStatus;
import com.horto.backend.infra.persistence.entities.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity, Integer> {
    Optional<SubscriptionEntity> findByUser_IdAndStatusOrderByEndDateDesc(Long userId, SubscriptionStatus status);

    Optional<SubscriptionEntity> findByUser_Id(Long userId);

    List<SubscriptionEntity> findByEndDateBefore(LocalDateTime endDateBefore);
}
