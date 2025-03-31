package com.horto.backend.infra.persistence.repositories;

import com.horto.backend.infra.persistence.entities.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<SupplierEntity, Long>, JpaSpecificationExecutor<SupplierEntity> {
    Optional<SupplierEntity> findByCnpj(String cnpj);

    Optional<SupplierEntity> findByName(String name);
}
