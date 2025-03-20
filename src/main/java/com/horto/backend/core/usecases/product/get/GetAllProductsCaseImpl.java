package com.horto.backend.core.usecases.product.get;

import com.horto.backend.core.entities.Product;
import com.horto.backend.core.gateway.ProductGateway;

import java.util.List;

public class GetAllProductsCaseImpl implements GetAllProductsCase {

    private final ProductGateway productGateway;

    public GetAllProductsCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public List<Product> execute() {
        return productGateway.getAllProducts();
    }
}
