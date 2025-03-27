package com.horto.backend.core.usecases.suppliers.get;

import com.horto.backend.core.entities.Supplier;
import com.horto.backend.core.gateway.SupplierGateway;

import java.util.List;

public class GetAllSuppliersCaseImpl implements GetAllSuppliersCase {

    private final SupplierGateway supplierGateway;

    public GetAllSuppliersCaseImpl(SupplierGateway supplierGateway) {
        this.supplierGateway = supplierGateway;
    }

    @Override
    public List<Supplier> execute() {
        return supplierGateway.getAllSuppliers();
    }
}
