package com.horto.backend.core.usecases.supplierOffer.get;

import com.horto.backend.core.gateway.SupplierOfferGateway;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOffersSummaryDTO;
import com.horto.backend.infra.filters.offer.OfferFilter;

import java.util.List;

public class GetOffersByProductCaseImpl implements GetOffersByProductCase {
    private final SupplierOfferGateway supplierOfferGateway;

    public GetOffersByProductCaseImpl(SupplierOfferGateway supplierOfferGateway) {
        this.supplierOfferGateway = supplierOfferGateway;
    }

    @Override
    public List<SupplierOffersSummaryDTO> execute(Long productId, OfferFilter filter) {
        return supplierOfferGateway.getOffersByProductId(productId, filter);
    }
}
