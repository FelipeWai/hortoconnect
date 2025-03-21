package com.horto.backend.core.exceptions.user.alreadyExists;

public class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException(String message) {
        super("Nome de usuário " + message + " já registrado.");
    }
}
