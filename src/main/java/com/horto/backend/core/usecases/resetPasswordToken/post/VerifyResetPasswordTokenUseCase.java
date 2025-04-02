package com.horto.backend.core.usecases.resetPasswordToken.post;

public interface VerifyResetPasswordTokenUseCase {

    Boolean execute(String email, String token);
}
