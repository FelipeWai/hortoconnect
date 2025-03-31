package com.horto.backend.infra.dto.supplierOffer.response;

import com.horto.backend.infra.dto.product.response.ProductNameResponseDTO;
import com.horto.backend.infra.dto.quality.response.QualityResponseDTO;
import com.horto.backend.infra.dto.size.response.SizeResponseDTO;
import com.horto.backend.infra.dto.supplier.response.SupplierResponseDTO;

public record SupplierOfferResponseDTO(
        Long id,
        ProductNameResponseDTO product,
        SupplierResponseDTO supplier,
        QualityResponseDTO quality,
        SizeResponseDTO size,
        Double minPrice,
        Double maxPrice
) {
}
