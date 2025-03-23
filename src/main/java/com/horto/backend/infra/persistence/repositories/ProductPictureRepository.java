package com.horto.backend.infra.persistence.repositories;

import com.horto.backend.infra.persistence.entities.ProductPictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductPictureRepository extends JpaRepository<ProductPictureEntity, Long> {
}
