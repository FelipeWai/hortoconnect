package com.horto.backend.infra.filters.product;

import lombok.Data;

@Data
public class ProductFilter {
    private Long category_id;
    private Long subcategory_id;
    private String name;
}
