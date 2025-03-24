package com.horto.backend.infra.persistence.repositories;

import com.horto.backend.infra.persistence.entities.FavoritesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoritesRepository extends JpaRepository<FavoritesEntity, Integer> {
    List<FavoritesEntity> findAllByUser_Id(Long userId);

    Optional<FavoritesEntity> findById(Long id);

    void deleteById(Long id);
}
