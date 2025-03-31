package com.horto.backend.core.usecases.supplierOffer.delete;

import com.horto.backend.core.gateway.SupplierOfferGateway;

public class DeleteOfferByIdCaseImpl implements DeleteOfferByIdCase {
    private final SupplierOfferGateway supplierOfferGateway;

    public DeleteOfferByIdCaseImpl(SupplierOfferGateway supplierOfferGateway) {
        this.supplierOfferGateway = supplierOfferGateway;
    }

    @Override
    public void execute(Long id) {
        supplierOfferGateway.deleteOfferById(id);
    }
}
