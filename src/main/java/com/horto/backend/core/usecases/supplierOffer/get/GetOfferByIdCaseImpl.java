package com.horto.backend.core.usecases.supplierOffer.get;

import com.horto.backend.core.entities.SupplierOffer;
import com.horto.backend.core.exceptions.supplierOffer.OneOfferNotFoundException;
import com.horto.backend.core.gateway.SupplierOfferGateway;

public class GetOfferByIdCaseImpl implements GetOfferByIdCase {

    private final SupplierOfferGateway supplierOfferGateway;

    public GetOfferByIdCaseImpl(SupplierOfferGateway supplierOfferGateway) {
        this.supplierOfferGateway = supplierOfferGateway;
    }

    @Override
    public SupplierOffer execute(Long id) {
        return supplierOfferGateway.getOfferById(id)
                .orElseThrow(() -> new OneOfferNotFoundException(id.toString()));
    }
}
