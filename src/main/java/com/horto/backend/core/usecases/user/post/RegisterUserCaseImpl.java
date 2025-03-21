package com.horto.backend.core.usecases.user.post;

import com.horto.backend.core.entities.User;
import com.horto.backend.core.gateway.UserGateway;
import com.horto.backend.infra.dto.user.request.RegisterRequestDTO;

public class RegisterUserCaseImpl implements RegisterUserCase {

    private final UserGateway userGateway;

    public RegisterUserCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public User execute(RegisterRequestDTO registerRequestDTO) {
        return userGateway.registerUser(registerRequestDTO);
    }
}
