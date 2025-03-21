package com.horto.backend.core.entities;

import com.horto.backend.core.enums.UserRoleEnum;

import java.time.LocalDateTime;

public record User(
        Long id,
        String username,
        String email,
        String password,
        String phone,
        String cnpj,
        UserRoleEnum role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
