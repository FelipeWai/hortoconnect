package com.horto.backend.core.exceptions.user.notFound;

public class UserNotFoundByIdException extends RuntimeException {
    public UserNotFoundByIdException(String message) {
        super("User not found with id " + message);
    }
}
