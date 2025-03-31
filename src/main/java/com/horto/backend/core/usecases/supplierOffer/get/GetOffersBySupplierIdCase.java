package com.horto.backend.core.usecases.supplierOffer.get;

import com.horto.backend.core.entities.SupplierOffer;

import java.util.List;

public interface GetOffersBySupplierIdCase {

    List<SupplierOffer> execute(Long supplierId);

}
