package com.horto.backend.core.usecases.suppliers.get;

import com.horto.backend.core.entities.Supplier;

import java.util.List;

public interface GetAllSuppliersCase {

    List<Supplier> execute();

}
