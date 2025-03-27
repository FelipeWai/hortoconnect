package com.horto.backend.core.usecases.suppliers.patch;

import com.horto.backend.core.entities.Supplier;
import com.horto.backend.core.gateway.SupplierGateway;
import com.horto.backend.infra.dto.supplier.request.SupplierPatchDTO;

public class PatchSupplierCaseImpl implements PatchSupplierCase {
    private final SupplierGateway supplierGateway;

    public PatchSupplierCaseImpl(SupplierGateway supplierGateway) {
        this.supplierGateway = supplierGateway;
    }

    @Override
    public Supplier execute(Long id, SupplierPatchDTO patchDTO) {
        return supplierGateway.patchSupplierById(id, patchDTO);
    }
}
