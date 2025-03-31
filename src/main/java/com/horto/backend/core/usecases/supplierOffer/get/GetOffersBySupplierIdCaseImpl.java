package com.horto.backend.core.usecases.supplierOffer.get;

import com.horto.backend.core.entities.SupplierOffer;
import com.horto.backend.core.gateway.SupplierOfferGateway;

import java.util.List;

public class GetOffersBySupplierIdCaseImpl implements GetOffersBySupplierIdCase {

    private final SupplierOfferGateway supplierOfferGateway;

    public GetOffersBySupplierIdCaseImpl(SupplierOfferGateway supplierOfferGateway) {
        this.supplierOfferGateway = supplierOfferGateway;
    }

    @Override
    public List<SupplierOffer> execute(Long supplierId) {
        return supplierOfferGateway.getOffersBySupplierId(supplierId);
    }
}
