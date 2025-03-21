package com.horto.backend.core.exceptions.user.notFound;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String message) {
        super("Username " + message + " não encontrado");
    }
}
