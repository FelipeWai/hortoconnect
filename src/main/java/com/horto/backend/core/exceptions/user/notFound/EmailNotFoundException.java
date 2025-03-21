package com.horto.backend.core.exceptions.user.notFound;

public class EmailNotFoundException extends RuntimeException {
    public EmailNotFoundException(String message) {
        super("Email " + message + " não encontrado");
    }
}
