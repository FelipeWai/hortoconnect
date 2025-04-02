package com.horto.backend.infra.persistence.repositories;

import com.horto.backend.infra.persistence.entities.ResetPasswordTokenEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordTokenEntity, Long> {
    Optional<ResetPasswordTokenEntity> findByEmailAndToken(String email, String token);

    @Transactional
    int deleteByExpirationTimeBefore(LocalDateTime now);
}
