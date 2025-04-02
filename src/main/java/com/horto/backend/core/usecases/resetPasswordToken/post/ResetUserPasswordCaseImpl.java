package com.horto.backend.core.usecases.resetPasswordToken.post;

import com.horto.backend.core.gateway.ResetPasswordTokenGateway;
import com.horto.backend.infra.dto.resetPassword.request.NewPasswordDTO;

public class ResetUserPasswordCaseImpl implements ResetUserPasswordCase {

    private final ResetPasswordTokenGateway resetPasswordTokenGateway;

    public ResetUserPasswordCaseImpl(ResetPasswordTokenGateway resetPasswordTokenGateway) {
        this.resetPasswordTokenGateway = resetPasswordTokenGateway;
    }

    @Override
    public void execute(NewPasswordDTO NewPasswordDTO) {
        resetPasswordTokenGateway.resetPassword(NewPasswordDTO);
    }
}
