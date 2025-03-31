package com.horto.backend.infra.persistence.repositories;

import com.horto.backend.core.entities.SupplierOffer;
import com.horto.backend.infra.persistence.entities.SupplierOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierOfferRepository extends JpaRepository<SupplierOfferEntity, Long> {
    List<SupplierOfferEntity> findAllBySupplier_Id(Long supplierId);

    List<SupplierOfferEntity> findByProduct_IdOrderByMinPriceAsc(Long productId);
}
