package com.horto.backend.core.usecases.resetPasswordToken.get;

import com.horto.backend.core.entities.ResetPasswordToken;
import com.horto.backend.core.exceptions.resetPasswordToken.TokenNotFoundException;
import com.horto.backend.core.gateway.ResetPasswordTokenGateway;

public class FindTokenByEmailAndTokenCaseImpl implements FindTokenByEmailAndTokenCase {

    private final ResetPasswordTokenGateway resetPasswordTokenGateway;

    public FindTokenByEmailAndTokenCaseImpl(ResetPasswordTokenGateway resetPasswordTokenGateway) {
        this.resetPasswordTokenGateway = resetPasswordTokenGateway;
    }

    @Override
    public ResetPasswordToken execute(String email, String token) {
        return resetPasswordTokenGateway.FindTokenByEmailAndToken(email, token)
                .orElseThrow(TokenNotFoundException::new);
    }
}
