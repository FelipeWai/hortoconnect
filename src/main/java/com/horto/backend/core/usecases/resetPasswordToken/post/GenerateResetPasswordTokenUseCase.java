package com.horto.backend.core.usecases.resetPasswordToken.post;

public interface GenerateResetPasswordTokenUseCase {
    void execute(String email);
}
