package com.horto.backend.core.usecases.product.get;

import com.horto.backend.core.entities.Product;
import com.horto.backend.core.gateway.ProductGateway;
import com.horto.backend.infra.filters.product.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class GetAllProductsCaseImpl implements GetAllProductsCase {

    private final ProductGateway productGateway;

    public GetAllProductsCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public Page<Product> execute(ProductFilter filter, Pageable pageable) {
        return productGateway.getAllProducts(filter, pageable);
    }

}
