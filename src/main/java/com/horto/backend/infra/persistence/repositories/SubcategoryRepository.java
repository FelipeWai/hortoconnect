package com.horto.backend.infra.persistence.repositories;

import com.horto.backend.infra.persistence.entities.SubcategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubcategoryRepository extends JpaRepository<SubcategoryEntity, Integer> {
    Optional<SubcategoryEntity> findById(Long id);

    void deleteById(Long id);
}
