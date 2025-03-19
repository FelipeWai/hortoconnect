package com.horto.backend.infra.persistence.repositories;

import com.horto.backend.infra.persistence.entities.QualityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QualityRepository extends JpaRepository<QualityEntity, Integer> {
    Optional<QualityEntity> findById(Long id);

    Optional<QualityEntity> findByName(String name);
}
