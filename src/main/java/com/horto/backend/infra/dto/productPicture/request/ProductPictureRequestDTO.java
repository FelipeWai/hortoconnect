package com.horto.backend.infra.dto.productPicture.request;

public record ProductPictureRequestDTO(
        String url,
        Long product_id
) {
}
