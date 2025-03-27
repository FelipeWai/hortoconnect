package com.horto.backend.core.usecases.suppliers.post;

import com.horto.backend.core.entities.Supplier;
import com.horto.backend.infra.dto.supplier.request.SupplierRequestDTO;

public interface CreateSupplierCase {

    Supplier execute(SupplierRequestDTO requestDTO);

}
