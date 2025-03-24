package com.horto.backend.core.usecases.product.get;

import com.horto.backend.core.entities.Product;
import com.horto.backend.infra.filters.product.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllProductsCase {

    Page<Product> execute(ProductFilter filter, Pageable pageable);

}
