package com.horto.backend.core.usecases.supplierOffer.get;

import com.horto.backend.core.entities.SupplierOffer;
import com.horto.backend.core.gateway.SupplierOfferGateway;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOffersSummaryDTO;

import java.util.List;

public class GetOffersByProductCaseImpl implements GetOffersByProductCase {
    private final SupplierOfferGateway supplierOfferGateway;

    public GetOffersByProductCaseImpl(SupplierOfferGateway supplierOfferGateway) {
        this.supplierOfferGateway = supplierOfferGateway;
    }

    @Override
    public List<SupplierOffersSummaryDTO> execute(Long productId) {
        return supplierOfferGateway.getOffersByProductId(productId);
    }
}
