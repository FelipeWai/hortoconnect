package com.horto.backend.infra.dto.user.request;

import com.horto.backend.core.enums.UserRoleEnum;
import com.horto.backend.infra.validator.ValidUserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public record RegisterRequestDTO(
        @NotNull(message = "Nome de usuário é obrigatório")
        @Size(min = 1, max = 50, message = "Tamanho do nome de usuário deve estar entre 1 e 50 caracteres.")
        String username,

        @Email(message = "Formato de e-mail inválido")
        @NotNull(message = "Email deve ser preenchido")
        @Size(min = 1, max = 100, message = "Tamanho do email deve estar entre 1 e 50 caracteres.")
        String email,

        @NotNull(message = "Senha deve ser preenchido")
        @Size(min = 1, max = 100, message = "Tamanho da senha deve estar entre 1 e 50 caracteres.")
        String password,

        @Pattern(
                regexp = "^\\(?\\d{2}\\)?\\s?(?:9\\d{4}|\\d{4})-?\\d{4}$",
                message = "Telefone inválido. Use o formato (XX) 9XXXX-XXXX ou (XX) XXXX-XXXX"
        )
        @NotNull(message = "Telefone deve ser preenchido")
        @Size(min = 1, max = 100, message = "Tamanho do telefone deve estar entre 1 e 50 caracteres.")
        String phone,

        @CNPJ(message = "CNPJ inválido")
        @NotNull(message = "CNPJ deve ser preenchido")
        @Size(min = 1, max = 18, message = "Tamanho do cnpj deve estar entre 1 e 18 caracteres.")
        String cnpj,

        @NotNull(message = "Role deve ser preenchido")
        @ValidUserRole
        UserRoleEnum role
) {
}
