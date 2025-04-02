package com.horto.backend.infra.dto.resetPassword.request;

import jakarta.validation.constraints.NotBlank;

public record VerifyResetPasswordDTO(
        @NotBlank(message = "O e-mail não pode ser vazio.") String email,
        @NotBlank(message = "O código de reset não pode ser vazio.") String token
) {}
