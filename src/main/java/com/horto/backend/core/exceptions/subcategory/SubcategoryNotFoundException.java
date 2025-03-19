package com.horto.backend.core.exceptions.subcategory;

public class SubcategoryNotFoundException extends RuntimeException {
    public SubcategoryNotFoundException(String message) {
        super("Subcategoria não encontrada com: " + message);
    }
}
