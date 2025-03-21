package com.horto.backend.core.usecases.user.post;

import com.horto.backend.core.entities.User;
import com.horto.backend.infra.dto.user.request.RegisterRequestDTO;

public interface RegisterUserCase {

    User execute(RegisterRequestDTO registerRequestDTO);

}
