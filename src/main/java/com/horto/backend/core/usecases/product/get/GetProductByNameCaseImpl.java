package com.horto.backend.core.usecases.product.get;

import com.horto.backend.core.entities.Product;
import com.horto.backend.core.exceptions.product.ProductNotFoundException;
import com.horto.backend.core.gateway.ProductGateway;

public class GetProductByNameCaseImpl implements GetProductByNameCase {
    private final ProductGateway productGateway;

    public GetProductByNameCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public Product getProductByName(String name) {
        return productGateway.getProductByName(name)
                .orElseThrow(() -> new ProductNotFoundException(name));
    }
}
