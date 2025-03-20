package com.horto.backend.core.usecases.product.post;

import com.horto.backend.core.entities.Product;
import com.horto.backend.core.gateway.ProductGateway;
import com.horto.backend.infra.dto.product.request.ProductRequestDTO;

public class CreateProductCaseImpl implements CreateProductCase {
    private final ProductGateway productGateway;

    public CreateProductCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public Product execute(ProductRequestDTO requestDTO) {
        return productGateway.createProduct(requestDTO);
    }
}
