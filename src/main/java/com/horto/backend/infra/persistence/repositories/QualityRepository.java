package com.horto.backend.infra.persistence.repositories;

import com.horto.backend.infra.persistence.entities.QualityEntity;
import com.horto.backend.infra.persistence.entities.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QualityRepository extends JpaRepository<QualityEntity, Integer> {
    Optional<QualityEntity> findById(Long id);

    Optional<QualityEntity> findByName(String name);

    List<QualityEntity> findAllByIdIn(List<Long> id);

    List<QualityEntity> findAllByOrderByNameAsc();

    void deleteById(Long id);
}
