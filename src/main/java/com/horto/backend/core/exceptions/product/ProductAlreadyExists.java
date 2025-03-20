package com.horto.backend.core.exceptions.product;

public class ProductAlreadyExists extends RuntimeException {
    public ProductAlreadyExists(String message) {
        super("Produto com nome " + message + " já existe");
    }
}
