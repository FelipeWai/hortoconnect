package com.horto.backend.core.usecases.supplierOffer.get;

import com.horto.backend.core.entities.SupplierOffer;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOfferResponseDTO;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOffersGroupedResponseDTO;

import java.util.List;

public interface GetOffersBySupplierIdCase {

    List<SupplierOffersGroupedResponseDTO<SupplierOfferResponseDTO>> execute(Long supplierId);

}
