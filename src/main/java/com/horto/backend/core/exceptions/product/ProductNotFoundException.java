package com.horto.backend.core.exceptions.product;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super("Produto Nao encontrado: " + message);
    }
}
