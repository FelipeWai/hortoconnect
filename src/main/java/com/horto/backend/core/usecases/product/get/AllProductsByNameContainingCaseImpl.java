package com.horto.backend.core.usecases.product.get;

import com.horto.backend.core.entities.Product;
import com.horto.backend.core.gateway.ProductGateway;

import java.util.List;

public class AllProductsByNameContainingCaseImpl implements AllProductsByNameContainingCase {

    private final ProductGateway productGateway;

    public AllProductsByNameContainingCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public List<Product> execute(String namePart) {
        return productGateway.getAllProductsByNameContaining(namePart);
    }
}
