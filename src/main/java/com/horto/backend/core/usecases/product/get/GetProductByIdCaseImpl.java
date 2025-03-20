package com.horto.backend.core.usecases.product.get;

import com.horto.backend.core.entities.Product;
import com.horto.backend.core.exceptions.product.ProductNotFoundException;
import com.horto.backend.core.gateway.ProductGateway;

public class GetProductByIdCaseImpl implements GetProductByIdCase {
    private final ProductGateway productGateway;

    public GetProductByIdCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public Product execute(Long id) {
        return productGateway.getProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));
    }
}
