package com.horto.backend.infra.dto.supplier.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

public record SupplierRequestDTO(
        @NotNull(message = "Nome de usuário é obrigatório")
        @Size(min = 1, max = 50, message = "Tamanho do nome de usuário deve estar entre 1 e 50 caracteres.")
        String name,

        @CNPJ(message = "CNPJ inválido")
        @NotNull(message = "CNPJ deve ser preenchido")
        @Size(min = 1, max = 18, message = "Tamanho do cnpj deve estar entre 1 e 18 caracteres.")
        String cnpj,

        @Pattern(
                regexp = "^\\(?\\d{2}\\)?\\s?(?:9\\d{4}|\\d{4})-?\\d{4}$",
                message = "Telefone inválido. Use o formato (XX) 9XXXX-XXXX ou (XX) XXXX-XXXX"
        )
        @NotNull(message = "Telefone de vendas deve ser preenchido")
        @Size(min = 1, max = 100, message = "Tamanho do telefone deve estar entre 1 e 50 caracteres.")
        String sales_phone,

        @Pattern(
                regexp = "^\\(?\\d{2}\\)?\\s?(?:9\\d{4}|\\d{4})-?\\d{4}$",
                message = "Telefone inválido. Use o formato (XX) 9XXXX-XXXX ou (XX) XXXX-XXXX"
        )
        @NotNull(message = "Telefone de contato deve ser preenchido")
        @Size(min = 1, max = 100, message = "Tamanho do telefone deve estar entre 1 e 50 caracteres.")
        String contact_phone
) {
}
