package com.horto.backend.core.usecases.supplierOffer.get;

import com.horto.backend.core.entities.SupplierOffer;
import com.horto.backend.core.gateway.SupplierOfferGateway;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOffersGroupedResponseDTO;

import java.util.List;

public class GetOffersByProductAndSupplierCaseImpl implements GetOffersByProductAndSupplierCase {

    private final SupplierOfferGateway supplierOfferGateway;

    public GetOffersByProductAndSupplierCaseImpl(SupplierOfferGateway supplierOfferGateway) {
        this.supplierOfferGateway = supplierOfferGateway;
    }

    @Override
    public SupplierOffersGroupedResponseDTO execute(Long productId, Long supplierId) {
        return supplierOfferGateway.getOffersByProductAndSupplierId(productId, supplierId);
    }
}
