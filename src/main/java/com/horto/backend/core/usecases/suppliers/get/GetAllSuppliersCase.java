package com.horto.backend.core.usecases.suppliers.get;

import com.horto.backend.core.entities.Supplier;
import com.horto.backend.infra.filters.supplier.SupplierFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllSuppliersCase {

    Page<Supplier> execute(SupplierFilter supplierFilter, Pageable pageable);

}
