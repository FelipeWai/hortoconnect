package com.horto.backend.core.usecases.resetPasswordToken.post;

import com.horto.backend.core.gateway.ResetPasswordTokenGateway;

public class GenerateResetPasswordTokenUseCaseImpl implements GenerateResetPasswordTokenUseCase {

    private final ResetPasswordTokenGateway resetPasswordTokenGateway;

    public GenerateResetPasswordTokenUseCaseImpl(ResetPasswordTokenGateway resetPasswordTokenGateway) {
        this.resetPasswordTokenGateway = resetPasswordTokenGateway;
    }


    @Override
    public void execute(String email) {
        resetPasswordTokenGateway.GenerateResetPasswordToken(email);
    }
}
