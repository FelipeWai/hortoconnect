package com.horto.backend.core.usecases.suppliers.get;

import com.horto.backend.core.entities.Supplier;
import com.horto.backend.core.exceptions.supplier.SupplierNotFoundException;
import com.horto.backend.core.gateway.SupplierGateway;

public class GetSupplierByIdCaseImpl implements GetSupplierByIdCase {

    private final SupplierGateway supplierGateway;

    public GetSupplierByIdCaseImpl(SupplierGateway supplierGateway) {
        this.supplierGateway = supplierGateway;
    }

    @Override
    public Supplier execute(Long id) {
        return supplierGateway.getSupplierById(id)
                .orElseThrow(() -> new SupplierNotFoundException(id.toString()));
    }
}
