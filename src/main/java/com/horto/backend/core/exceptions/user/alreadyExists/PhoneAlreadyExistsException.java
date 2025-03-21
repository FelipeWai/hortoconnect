package com.horto.backend.core.exceptions.user.alreadyExists;

public class PhoneAlreadyExistsException extends RuntimeException {
    public PhoneAlreadyExistsException(String message) {
        super("Telefone " + message + " já registrado.");
    }
}
