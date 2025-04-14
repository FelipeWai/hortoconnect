package com.horto.backend.core.usecases.supplierOffer.get;

import com.horto.backend.infra.dto.supplierOffer.response.SupplierOffersGroupedResponseDTO;

public interface GetOffersByProductAndSupplierCase {
    SupplierOffersGroupedResponseDTO execute(Long productId, Long supplierId);
}
