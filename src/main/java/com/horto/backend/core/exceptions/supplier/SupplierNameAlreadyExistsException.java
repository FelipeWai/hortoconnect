package com.horto.backend.core.exceptions.supplier;

public class SupplierNameAlreadyExistsException extends RuntimeException {
    public SupplierNameAlreadyExistsException(String message) {
        super("Fornecedor já registrado com o nome " + message);
    }
}
