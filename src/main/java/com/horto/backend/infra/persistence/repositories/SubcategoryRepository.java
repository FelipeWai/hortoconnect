package com.horto.backend.infra.persistence.repositories;

import com.horto.backend.infra.persistence.entities.SubcategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubcategoryRepository extends JpaRepository<SubcategoryEntity, Integer> {
    Optional<SubcategoryEntity> findById(Long id);

    List<SubcategoryEntity> findByNameContainingIgnoreCase(String name);

    void deleteById(Long id);

    Optional<SubcategoryEntity> findByName(String name);

    List<SubcategoryEntity> findAllByCategory_Id(Long categoryId);
}
