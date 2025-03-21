package com.horto.backend.core.exceptions.user.alreadyExists;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super("Email " + message + " já registrado.");
    }
}
