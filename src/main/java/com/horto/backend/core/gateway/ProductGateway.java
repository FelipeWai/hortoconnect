package com.horto.backend.core.gateway;

import com.horto.backend.core.entities.Product;
import com.horto.backend.infra.dto.product.request.ProductPatchDTO;
import com.horto.backend.infra.dto.product.request.ProductRequestDTO;

import java.util.List;
import java.util.Optional;

public interface ProductGateway {

    List<Product> getAllProducts();

    Optional<Product> getProductById(Long id);

    Optional<Product> getProductByName(String name);

    Product createProduct(ProductRequestDTO requestDTO);

    void deleteProductById(Long id);

    Product patchProductById(Long id, ProductPatchDTO patchDTO);

}
