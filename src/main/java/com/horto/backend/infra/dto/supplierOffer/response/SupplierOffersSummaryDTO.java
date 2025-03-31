package com.horto.backend.infra.dto.supplierOffer.response;

import com.horto.backend.infra.dto.supplier.response.SupplierNameDTO;

import java.util.List;

public record SupplierOffersSummaryDTO(
        SupplierNameDTO supplier,
        List<String> qualities,
        List<String> sizes,
        PriceRangeDTO priceRange
) {
}
