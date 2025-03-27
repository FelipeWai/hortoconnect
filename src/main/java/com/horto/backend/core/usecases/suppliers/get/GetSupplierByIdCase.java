package com.horto.backend.core.usecases.suppliers.get;

import com.horto.backend.core.entities.Supplier;

public interface GetSupplierByIdCase {

    Supplier execute(Long id);

}
