package com.horto.backend.core.exceptions.resetPasswordToken;

public class NewPasswordSameAsOldException extends RuntimeException {
    public NewPasswordSameAsOldException() {
        super("A nova senha não pode ser igual a uma das últimas 3 senhas.");
    }
}
