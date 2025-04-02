package com.horto.backend.infra.dto.resetPassword.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequestDTO(
        @Email @NotBlank(message = "O e-mail não pode ser vazio.") String email
) {
}
