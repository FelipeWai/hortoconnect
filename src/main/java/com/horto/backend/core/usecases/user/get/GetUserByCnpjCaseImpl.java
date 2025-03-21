package com.horto.backend.core.usecases.user.get;

import com.horto.backend.core.entities.User;
import com.horto.backend.core.exceptions.user.alreadyExists.CnpjAlreadyExistsException;
import com.horto.backend.core.exceptions.user.notFound.CnpjNotFoundException;
import com.horto.backend.core.gateway.UserGateway;

public class GetUserByCnpjCaseImpl implements GetUserByCnpjCase {

    private final UserGateway userGateway;

    public GetUserByCnpjCaseImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public User execute(String cnpj) {
        return userGateway.getUserByCnpj(cnpj)
                .orElseThrow(() -> new CnpjNotFoundException(cnpj));
    }
}
