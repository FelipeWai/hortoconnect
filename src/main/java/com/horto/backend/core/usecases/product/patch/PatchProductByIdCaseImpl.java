package com.horto.backend.core.usecases.product.patch;

import com.horto.backend.core.entities.Product;
import com.horto.backend.core.gateway.ProductGateway;
import com.horto.backend.infra.dto.product.request.ProductPatchDTO;

public class PatchProductByIdCaseImpl implements PatchProductByIdCase {
    private final ProductGateway productGateway;

    public PatchProductByIdCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public Product execute(Long id, ProductPatchDTO patchDTO) {
        return productGateway.patchProductById(id, patchDTO);
    }
}
