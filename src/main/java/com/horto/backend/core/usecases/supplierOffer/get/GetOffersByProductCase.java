package com.horto.backend.core.usecases.supplierOffer.get;

import com.horto.backend.core.entities.SupplierOffer;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOffersSummaryDTO;

import java.util.List;

public interface GetOffersByProductCase {

    List<SupplierOffersSummaryDTO> execute(Long productId);

}
