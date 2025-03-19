package com.horto.backend.infra.persistence.repositories;

import com.horto.backend.core.entities.Size;
import com.horto.backend.infra.persistence.entities.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SizeRepository extends JpaRepository<SizeEntity, Integer> {
    Optional<SizeEntity> findById(Long id);

    Optional<SizeEntity> findByName(String name);

    List<SizeEntity> findAllByIdIn(List<Long> ids);
}
