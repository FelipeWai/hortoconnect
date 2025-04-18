package com.horto.backend.infra.dto.supplierOffer.response;

import com.horto.backend.infra.dto.quality.response.QualityResponseDTO;
import com.horto.backend.infra.dto.size.response.SizeResponseDTO;

public record SupplierOfferResponseDTO(
        Long id,
        QualityResponseDTO quality,
        SizeResponseDTO size,
        Double minPrice,
        Double maxPrice
) {
}
