package com.horto.backend.core.usecases.supplierOffer.post;

import com.horto.backend.core.entities.SupplierOffer;
import com.horto.backend.infra.dto.supplierOffer.request.SupplierOfferRequestDTO;

public interface CreateOfferCase {

    SupplierOffer execute(SupplierOfferRequestDTO requestDTO);

}
