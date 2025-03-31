package com.horto.backend.core.usecases.supplierOffer.post;

import com.horto.backend.core.entities.SupplierOffer;
import com.horto.backend.core.gateway.SupplierOfferGateway;
import com.horto.backend.infra.dto.supplierOffer.request.SupplierOfferRequestDTO;

public class CreateOfferCaseImpl implements CreateOfferCase {

    private final SupplierOfferGateway supplierOfferGateway;

    public CreateOfferCaseImpl(SupplierOfferGateway supplierOfferGateway) {
        this.supplierOfferGateway = supplierOfferGateway;
    }

    @Override
    public SupplierOffer execute(SupplierOfferRequestDTO requestDTO) {
        return supplierOfferGateway.createOffer(requestDTO);
    }
}
