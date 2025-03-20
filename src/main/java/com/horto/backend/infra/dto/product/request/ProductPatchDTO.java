package com.horto.backend.infra.dto.product.request;

import java.util.Optional;

public record ProductPatchDTO(
        Optional<String> name,
        Optional<Long> subcategory_id
) {
}
