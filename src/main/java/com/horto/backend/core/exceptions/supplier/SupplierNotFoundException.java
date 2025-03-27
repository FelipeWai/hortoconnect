package com.horto.backend.core.exceptions.supplier;

public class SupplierNotFoundException extends RuntimeException {
    public SupplierNotFoundException(String message) {
        super("Fornecedor não encontrado para: " + message);
    }
}
