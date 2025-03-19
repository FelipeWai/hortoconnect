package com.horto.backend.infra.persistence.repositories;

import com.horto.backend.infra.persistence.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    Optional<CategoryEntity> findById(Long id);

    Optional<CategoryEntity> findByName(String name);

    void deleteById(Long id);
}
