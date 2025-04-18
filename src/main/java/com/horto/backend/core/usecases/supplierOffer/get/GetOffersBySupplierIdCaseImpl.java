package com.horto.backend.core.usecases.supplierOffer.get;

import com.horto.backend.core.entities.SupplierOffer;
import com.horto.backend.core.gateway.SupplierOfferGateway;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOfferResponseDTO;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOffersGroupedResponseDTO;

import java.util.List;

public class GetOffersBySupplierIdCaseImpl implements GetOffersBySupplierIdCase {

    private final SupplierOfferGateway supplierOfferGateway;

    public GetOffersBySupplierIdCaseImpl(SupplierOfferGateway supplierOfferGateway) {
        this.supplierOfferGateway = supplierOfferGateway;
    }

    @Override
    public List<SupplierOffersGroupedResponseDTO<SupplierOfferResponseDTO>> execute(Long supplierId) {
        return supplierOfferGateway.getOffersBySupplierId(supplierId);
    }
}
