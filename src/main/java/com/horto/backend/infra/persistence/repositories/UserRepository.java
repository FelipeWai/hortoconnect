package com.horto.backend.infra.persistence.repositories;

import com.horto.backend.core.entities.User;
import com.horto.backend.infra.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByCnpj(String cnpj);

    Optional<UserEntity> findByPhone(String phone);

    Optional<UserEntity> findByUsername(String username);
}
