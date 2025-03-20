package com.horto.backend.infra.dto.product.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProductRequestDTO(

        @NotNull(message = "É necessário enviar um nome para o produto")
        @Size(min = 1, max = 255)
        String name,

        @NotNull(message = "É necessário enviar uma subcategoria")
        Long subcategory_id

) {}
