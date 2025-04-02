package com.horto.backend.core.usecases.resetPasswordToken.post;

import com.horto.backend.core.gateway.ResetPasswordTokenGateway;

public class VerifyResetPasswordTokenUseCaseImpl implements VerifyResetPasswordTokenUseCase {

    private final ResetPasswordTokenGateway resetPasswordTokenGateway;

    public VerifyResetPasswordTokenUseCaseImpl(ResetPasswordTokenGateway resetPasswordTokenGateway) {
        this.resetPasswordTokenGateway = resetPasswordTokenGateway;
    }

    @Override
    public Boolean execute(String email, String token) {
        return resetPasswordTokenGateway.VerifyResetPasswordToken(email, token);
    }
}
