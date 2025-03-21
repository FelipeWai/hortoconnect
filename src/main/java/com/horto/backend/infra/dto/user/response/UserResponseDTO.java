package com.horto.backend.infra.dto.user.response;

import com.horto.backend.core.enums.UserRoleEnum;

public record UserResponseDTO(
        Long id,
        String username,
        String email,
        String phone,
        String cnpj,
        UserRoleEnum role
) {
}
