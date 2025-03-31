package com.horto.backend.core.usecases.supplierOffer.get;

import com.horto.backend.core.entities.SupplierOffer;
import com.horto.backend.core.gateway.SupplierOfferGateway;

import java.util.List;

public class GetOffersByProductAndSupplierCaseImpl implements GetOffersByProductAndSupplierCase {

    private final SupplierOfferGateway supplierOfferGateway;

    public GetOffersByProductAndSupplierCaseImpl(SupplierOfferGateway supplierOfferGateway) {
        this.supplierOfferGateway = supplierOfferGateway;
    }

    @Override
    public List<SupplierOffer> execute(Long productId, Long supplierId) {
        return supplierOfferGateway.getOffersByProductAndSupplierId(productId, supplierId);
    }
}
