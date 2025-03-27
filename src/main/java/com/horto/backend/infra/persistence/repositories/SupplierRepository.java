package com.horto.backend.infra.persistence.repositories;

import com.horto.backend.infra.persistence.entities.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {
    Optional<SupplierEntity> findByCnpj(String cnpj);

    Optional<SupplierEntity> findByName(String name);
}
