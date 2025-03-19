package com.horto.backend.infra.dto.subcategory.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record SubcategoryRequestDTO(
        @NotBlank(message = "Nome da subcategoria não pode ser nulo")
        @Size(min = 2, max = 100)
        String name,

        @NotNull(message = "Categoria atrelada não pode ser nulo")
        Long category_id,

        @NotNull(message = "Subcategoria não pode ser criada sem qualidades")
        @Size(min = 1, message = "A subcategoria deve ter pelo menos uma qualidade")
        List<Long> qualities_id,

        @NotNull(message = "Subcategoria não pode ser criada sem tamanhos")
        @Size(min = 1, message = "A subcategoria deve ter pelo menos uma qualidade")
        List<Long> sizes_id
) {
}
