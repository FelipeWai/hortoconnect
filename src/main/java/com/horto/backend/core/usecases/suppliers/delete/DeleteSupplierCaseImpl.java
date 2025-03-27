package com.horto.backend.core.usecases.suppliers.delete;

import com.horto.backend.core.gateway.SupplierGateway;

public class DeleteSupplierCaseImpl implements DeleteSupplierCase {
    private final SupplierGateway supplierGateway;

    public DeleteSupplierCaseImpl(SupplierGateway supplierGateway) {
        this.supplierGateway = supplierGateway;
    }

    @Override
    public void execute(Long supplierId) {
        supplierGateway.deleteSupplierById(supplierId);
    }
}
