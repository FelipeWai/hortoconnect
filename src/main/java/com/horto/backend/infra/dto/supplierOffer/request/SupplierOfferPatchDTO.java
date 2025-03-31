package com.horto.backend.infra.dto.supplierOffer.request;

import jakarta.validation.constraints.AssertTrue;

import java.util.Optional;

public record SupplierOfferPatchDTO(
        Optional<Double> minPrice,
        Optional<Double> maxPrice
) {
}
