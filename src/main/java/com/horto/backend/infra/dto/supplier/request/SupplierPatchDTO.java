package com.horto.backend.infra.dto.supplier.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.Optional;

public record SupplierPatchDTO(
        Optional<@Size(min = 1, max = 50, message = "Tamanho do nome de usuário deve estar entre 1 e 50 caracteres.") String> name,

        Optional<@CNPJ(message = "CNPJ inválido")
        @Size(min = 1, max = 18, message = "Tamanho do CNPJ deve estar entre 1 e 18 caracteres.") String> cnpj,

        Optional<@Pattern(
                regexp = "^\\(?\\d{2}\\)?\\s?(?:9\\d{4}|\\d{4})-?\\d{4}$",
                message = "Telefone inválido. Use o formato (XX) 9XXXX-XXXX ou (XX) XXXX-XXXX"
        ) @Size(min = 1, max = 100, message = "Tamanho do telefone deve estar entre 1 e 50 caracteres.") String> sales_phone,

        Optional<@Pattern(
                regexp = "^\\(?\\d{2}\\)?\\s?(?:9\\d{4}|\\d{4})-?\\d{4}$",
                message = "Telefone inválido. Use o formato (XX) 9XXXX-XXXX ou (XX) XXXX-XXXX"
        ) @Size(min = 1, max = 100, message = "Tamanho do telefone deve estar entre 1 e 50 caracteres.") String> contact_phone
) {
}

