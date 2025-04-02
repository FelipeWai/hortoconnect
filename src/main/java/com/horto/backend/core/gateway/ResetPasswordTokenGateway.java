package com.horto.backend.core.gateway;

import com.horto.backend.core.entities.ResetPasswordToken;
import com.horto.backend.infra.dto.resetPassword.request.NewPasswordDTO;

import java.util.Optional;

public interface ResetPasswordTokenGateway {

    Optional<ResetPasswordToken> FindTokenByEmailAndToken(String email, String token);

    void GenerateResetPasswordToken(String email);

    Boolean VerifyResetPasswordToken(String email, String token);

    void resetPassword(NewPasswordDTO NewPasswordDTO);
}
