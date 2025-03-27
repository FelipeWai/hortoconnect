package com.horto.backend.core.usecases.suppliers.post;

import com.horto.backend.core.entities.Supplier;
import com.horto.backend.core.gateway.SupplierGateway;
import com.horto.backend.infra.dto.supplier.request.SupplierRequestDTO;

public class CreateSupplierCaseImpl implements CreateSupplierCase {

    private final SupplierGateway supplierGateway;

    public CreateSupplierCaseImpl(SupplierGateway supplierGateway) {
        this.supplierGateway = supplierGateway;
    }

    @Override
    public Supplier execute(SupplierRequestDTO requestDTO) {
        return supplierGateway.createSupplier(requestDTO);
    }
}
