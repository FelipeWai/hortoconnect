package com.horto.backend.infra.persistence.repositories;

import com.horto.backend.infra.filters.product.ProductSpecification;
import com.horto.backend.infra.persistence.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {
    Optional<ProductEntity> findByName(String name);

    Page<ProductEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
