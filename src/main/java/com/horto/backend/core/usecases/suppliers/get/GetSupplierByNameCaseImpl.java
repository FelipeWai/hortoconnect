package com.horto.backend.core.usecases.suppliers.get;

import com.horto.backend.core.entities.Supplier;
import com.horto.backend.core.exceptions.supplier.SupplierNotFoundException;
import com.horto.backend.core.gateway.SupplierGateway;

public class GetSupplierByNameCaseImpl implements GetSupplierByNameCase {
    private final SupplierGateway supplierGateway;

    public GetSupplierByNameCaseImpl(SupplierGateway supplierGateway) {
        this.supplierGateway = supplierGateway;
    }

    @Override
    public Supplier execute(String supplierName) {
        return supplierGateway.getSupplierByName(supplierName)
                .orElseThrow(() -> new SupplierNotFoundException(supplierName));
    }
}
