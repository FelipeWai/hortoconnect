package com.horto.backend.core.exceptions.category;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) {
        super("Categoria não encontrada com: " + message);
    }
}
