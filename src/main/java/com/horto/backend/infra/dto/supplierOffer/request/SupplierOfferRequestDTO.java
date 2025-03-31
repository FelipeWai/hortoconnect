package com.horto.backend.infra.dto.supplierOffer.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record SupplierOfferRequestDTO(
        @NotNull(message = "O ID do produto é obrigatório.")
        Long product_id,

        @NotNull(message = "O ID do fornecedor é obrigatório.")
        Long supplier_id,

        @NotNull(message = "O ID da qualidade é obrigatório.")
        Long quality_id,

        @NotNull(message = "O ID do tamanho é obrigatório.")
        Long size_id,

        @DecimalMin(value = "0.0", inclusive = false, message = "O preço mínimo deve ser maior que zero.")
        Double minPrice,

        @DecimalMin(value = "0.0", inclusive = false, message = "O preço máximo deve ser maior que zero.")
        Double maxPrice
) {
    @AssertTrue(message = "O preço mínimo não pode ser maior que o preço máximo.")
    public boolean isValidPriceRange() {
        return minPrice == null || maxPrice == null || minPrice <= maxPrice;
    }
}
