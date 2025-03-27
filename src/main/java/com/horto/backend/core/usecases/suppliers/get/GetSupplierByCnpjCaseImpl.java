package com.horto.backend.core.usecases.suppliers.get;

import com.horto.backend.core.entities.Supplier;
import com.horto.backend.core.exceptions.user.notFound.CnpjNotFoundException;
import com.horto.backend.core.gateway.SupplierGateway;

public class GetSupplierByCnpjCaseImpl implements GetSupplierByCnpjCase {
    private final SupplierGateway supplierGateway;

    public GetSupplierByCnpjCaseImpl(SupplierGateway supplierGateway) {
        this.supplierGateway = supplierGateway;
    }

    @Override
    public Supplier execute(String cnpj) {
        return supplierGateway.getSupplierByCnpj(cnpj)
                .orElseThrow(() -> new CnpjNotFoundException(cnpj));
    }
}
