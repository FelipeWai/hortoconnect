package com.horto.backend.core.usecases.user.get;

import com.horto.backend.core.entities.User;
import com.horto.backend.core.exceptions.user.alreadyExists.EmailAlreadyExistsException;
import com.horto.backend.core.exceptions.user.notFound.EmailNotFoundException;
import com.horto.backend.core.gateway.UserGateway;

public class GetUserByEmailCaseImpl implements GetUserByEmailCase {

    private final UserGateway userGateway;

    public GetUserByEmailCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public User execute(String email) {
        return userGateway.getUserByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(email));
    }
}
