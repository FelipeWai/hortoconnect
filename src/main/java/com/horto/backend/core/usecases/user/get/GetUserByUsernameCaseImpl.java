package com.horto.backend.core.usecases.user.get;

import com.horto.backend.core.entities.User;
import com.horto.backend.core.gateway.UserGateway;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class GetUserByUsernameCaseImpl implements GetUserByUsernameCase {
    private final UserGateway userGateway;

    public GetUserByUsernameCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public User execute(String username) {
        return userGateway.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
