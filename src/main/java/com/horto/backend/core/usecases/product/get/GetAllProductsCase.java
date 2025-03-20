package com.horto.backend.core.usecases.product.get;

import com.horto.backend.core.entities.Product;

import java.util.List;

public interface GetAllProductsCase {

    List<Product> execute();

}
