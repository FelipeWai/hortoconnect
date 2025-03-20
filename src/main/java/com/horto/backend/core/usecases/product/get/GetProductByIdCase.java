package com.horto.backend.core.usecases.product.get;

import com.horto.backend.core.entities.Product;

public interface GetProductByIdCase {
    Product execute(Long id);
}
