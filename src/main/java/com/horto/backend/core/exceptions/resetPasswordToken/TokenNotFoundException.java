package com.horto.backend.core.exceptions.resetPasswordToken;

public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException() {
        super("Token inválido ou expirado.");
    }
}
