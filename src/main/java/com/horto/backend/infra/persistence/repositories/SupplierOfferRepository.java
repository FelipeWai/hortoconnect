package com.horto.backend.infra.persistence.repositories;

import com.horto.backend.infra.persistence.entities.SupplierOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SupplierOfferRepository extends JpaRepository<SupplierOfferEntity, Long> {
    List<SupplierOfferEntity> findAllBySupplier_Id(Long supplierId);

    List<SupplierOfferEntity> findByProduct_IdOrderByMinPriceAsc(Long productId);

    List<SupplierOfferEntity> findAllByProduct_IdAndSupplier_Id(Long productId, Long supplierId);

    @Query("SELECT so FROM SupplierOfferEntity so WHERE so.supplier.id = :supplierId AND so.product.id = :productId AND so.size.id = :sizeId AND so.quality.id = :qualityId")
    Optional<SupplierOfferEntity> findBySupplierAndProductAndSizeAndQualityIds(
            @Param("supplierId") Long supplierId,
            @Param("productId") Long productId,
            @Param("sizeId") Long sizeId,
            @Param("qualityId") Long qualityId
    );
}
