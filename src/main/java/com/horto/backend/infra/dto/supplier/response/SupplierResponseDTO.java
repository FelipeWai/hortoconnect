package com.horto.backend.infra.dto.supplier.response;

public record SupplierResponseDTO(
        Long id,
        String name,
        String cnpj,
        String sales_phone,
        String contact_phone
) {
}
