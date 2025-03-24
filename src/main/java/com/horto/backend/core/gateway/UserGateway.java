package com.horto.backend.core.gateway;

import com.horto.backend.core.entities.User;
import com.horto.backend.infra.dto.user.request.RegisterRequestDTO;

import java.util.Optional;

public interface UserGateway {

    User registerUser(RegisterRequestDTO registerRequestDTO);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByCnpj(String cnpj);

    Optional<User> getUserByPhone(String phone);

    Optional<User> getUserByUsername(String username);

    Optional<User> getUserById(Long id);

}
