package com.horto.backend.infra.dto.subcategory.request;

import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Optional;

public record SubcategoryPatchDTO(
        Optional<@Size(min = 1, max = 50, message = "Tamanho do nome de usuário deve estar entre 1 e 50 caracteres.") String> name,
        Optional<Long> category_id,
        Optional<@Size(min = 1, message = "A subcategoria deve ter pelo menos uma qualidade") List<Long>> qualities_id,
        Optional<@Size(min = 1, message = "A subcategoria deve ter pelo menos um tamanho") List<Long>> sizes_id
) {}
