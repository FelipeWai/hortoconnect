package com.horto.backend.core.entities;

public record Supplier(
        Long id,
        String name,
        String cnpj,
        String sales_phone,
        String contact_phone
) {
}
