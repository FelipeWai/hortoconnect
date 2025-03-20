package com.horto.backend.core.usecases.product.patch;

import com.horto.backend.core.entities.Product;
import com.horto.backend.infra.dto.product.request.ProductPatchDTO;

public interface PatchProductByIdCase {

    Product execute(Long id, ProductPatchDTO patchDTO);

}
