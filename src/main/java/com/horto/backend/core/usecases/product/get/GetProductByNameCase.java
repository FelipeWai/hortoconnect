package com.horto.backend.core.usecases.product.get;

import com.horto.backend.core.entities.Product;

public interface GetProductByNameCase {
    Product getProductByName(String name);
}
