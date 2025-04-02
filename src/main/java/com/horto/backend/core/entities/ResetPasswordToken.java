package com.horto.backend.core.entities;

import java.time.LocalDateTime;

public record ResetPasswordToken(
        Long id,
        String email,
        String token,
        LocalDateTime expirationTime,
        Boolean used
) {

    public ResetPasswordToken(String email, String token, LocalDateTime expirationTime) {
        this(null, email, token, expirationTime, false);
    }
}
