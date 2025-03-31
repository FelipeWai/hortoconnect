package com.horto.backend.core.usecases.supplierOffer.patch;

import com.horto.backend.core.entities.SupplierOffer;
import com.horto.backend.infra.dto.supplierOffer.request.SupplierOfferPatchDTO;

public interface PatchOfferByIdCase {

    SupplierOffer execute(Long id, SupplierOfferPatchDTO patchDTO);
}
