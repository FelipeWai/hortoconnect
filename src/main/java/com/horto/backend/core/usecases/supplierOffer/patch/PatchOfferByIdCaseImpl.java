package com.horto.backend.core.usecases.supplierOffer.patch;

import com.horto.backend.core.entities.SupplierOffer;
import com.horto.backend.core.gateway.SupplierOfferGateway;
import com.horto.backend.infra.dto.supplierOffer.request.SupplierOfferPatchDTO;

public class PatchOfferByIdCaseImpl implements PatchOfferByIdCase {
    private final SupplierOfferGateway supplierOfferGateway;

    public PatchOfferByIdCaseImpl(SupplierOfferGateway supplierOfferGateway) {
        this.supplierOfferGateway = supplierOfferGateway;
    }

    @Override
    public SupplierOffer execute(Long id, SupplierOfferPatchDTO patchDTO) {
        return supplierOfferGateway.patchOfferById(id, patchDTO);
    }
}
