package com.horto.backend.core.usecases.suppliers.get;

import com.horto.backend.core.entities.Supplier;
import com.horto.backend.core.gateway.SupplierGateway;
import com.horto.backend.infra.filters.supplier.SupplierFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GetAllSuppliersCaseImpl implements GetAllSuppliersCase {

    private final SupplierGateway supplierGateway;

    public GetAllSuppliersCaseImpl(SupplierGateway supplierGateway) {
        this.supplierGateway = supplierGateway;
    }

    @Override
    public Page<Supplier> execute(SupplierFilter supplierFilter, Pageable pageable) {
        return supplierGateway.getAllSuppliers(supplierFilter, pageable);
    }
}
