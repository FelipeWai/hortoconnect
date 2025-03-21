package com.horto.backend.core.exceptions.user.notFound;

public class PhoneNotFoundException extends RuntimeException {
    public PhoneNotFoundException(String message) {
        super("Telefone " + message + " não encontrado");
    }
}
