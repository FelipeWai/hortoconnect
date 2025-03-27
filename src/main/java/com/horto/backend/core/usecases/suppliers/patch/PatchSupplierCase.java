package com.horto.backend.core.usecases.suppliers.patch;

import com.horto.backend.core.entities.Supplier;
import com.horto.backend.infra.dto.supplier.request.SupplierPatchDTO;

public interface PatchSupplierCase {
    Supplier execute(Long id, SupplierPatchDTO patchDTO);
}
