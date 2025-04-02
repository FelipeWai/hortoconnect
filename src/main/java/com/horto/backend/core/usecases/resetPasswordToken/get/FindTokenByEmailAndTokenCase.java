package com.horto.backend.core.usecases.resetPasswordToken.get;

import com.horto.backend.core.entities.ResetPasswordToken;

public interface FindTokenByEmailAndTokenCase {

    ResetPasswordToken execute(String email, String token);

}
