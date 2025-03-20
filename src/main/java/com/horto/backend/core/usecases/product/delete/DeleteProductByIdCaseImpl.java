package com.horto.backend.core.usecases.product.delete;

import com.horto.backend.core.gateway.ProductGateway;

public class DeleteProductByIdCaseImpl implements DeleteProductByIdCase {

    private final ProductGateway productGateway;

    public DeleteProductByIdCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }


    @Override
    public void execute(Long id) {
        productGateway.deleteProductById(id);
    }
}
