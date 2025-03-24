package com.horto.backend.core.usecases.user.get;

import com.horto.backend.core.entities.User;
import com.horto.backend.core.exceptions.user.notFound.UserNotFoundByIdException;
import com.horto.backend.core.gateway.UserGateway;

public class GetUserByIdCaseImpl implements GetUserByIdCase {

    private final UserGateway userGateway;

    public GetUserByIdCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public User execute(Long id) {
        return userGateway.getUserById(id)
                .orElseThrow(() -> new UserNotFoundByIdException(id.toString()));
    }
}
