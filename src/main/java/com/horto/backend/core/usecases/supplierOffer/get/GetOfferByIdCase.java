package com.horto.backend.core.usecases.supplierOffer.get;

import com.horto.backend.core.entities.SupplierOffer;

public interface GetOfferByIdCase {

    SupplierOffer execute(Long id);

}
