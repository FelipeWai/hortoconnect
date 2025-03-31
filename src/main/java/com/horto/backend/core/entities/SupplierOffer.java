package com.horto.backend.core.entities;

public record SupplierOffer(
        Long id,
        Product product,
        Supplier supplier,
        Quality quality,
        Size size,
        Double minPrice,
        Double maxPrice
) {
}
