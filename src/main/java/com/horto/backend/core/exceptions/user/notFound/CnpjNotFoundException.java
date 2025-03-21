package com.horto.backend.core.exceptions.user.notFound;

public class CnpjNotFoundException extends RuntimeException {
    public CnpjNotFoundException(String message) {
        super("Cnpj " + message + " não encontrado");
    }
}
