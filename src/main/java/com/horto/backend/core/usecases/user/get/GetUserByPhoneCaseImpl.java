package com.horto.backend.core.usecases.user.get;

import com.horto.backend.core.entities.User;
import com.horto.backend.core.exceptions.user.alreadyExists.PhoneAlreadyExistsException;
import com.horto.backend.core.exceptions.user.notFound.PhoneNotFoundException;
import com.horto.backend.core.gateway.UserGateway;

public class GetUserByPhoneCaseImpl implements GetUserByPhoneCase {
    private final UserGateway userGateway;

    public GetUserByPhoneCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public User execute(String phone) {
        return userGateway.getUserByPhone(phone)
                .orElseThrow(() -> new PhoneNotFoundException(phone));
    }
}
