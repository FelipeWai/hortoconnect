package com.horto.backend.core.exceptions.user.alreadyExists;

public class CnpjAlreadyExistsException extends RuntimeException {
    public CnpjAlreadyExistsException(String message) {
        super("CNPJ " + message + " já registrado.");
    }
}
