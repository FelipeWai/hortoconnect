package com.horto.backend.infra.dto.subcategory.request;

import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Optional;

public record SubcategoryPatchDTO(
        Optional<String> name,
        Optional<Long> category_id,
        Optional<@Size(min = 1, message = "A subcategoria deve ter pelo menos uma qualidade") List<Long>> qualities_id,
        Optional<@Size(min = 1, message = "A subcategoria deve ter pelo menos um tamanho") List<Long>> sizes_id
) {}
