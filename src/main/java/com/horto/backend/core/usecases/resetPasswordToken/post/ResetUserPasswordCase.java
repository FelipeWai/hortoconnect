package com.horto.backend.core.usecases.resetPasswordToken.post;

import com.horto.backend.infra.dto.resetPassword.request.NewPasswordDTO;

public interface ResetUserPasswordCase {

    void execute(NewPasswordDTO NewPasswordDTO);

}
