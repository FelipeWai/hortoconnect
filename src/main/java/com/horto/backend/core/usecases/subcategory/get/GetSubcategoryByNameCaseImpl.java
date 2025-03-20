package com.horto.backend.core.usecases.subcategory.get;

import com.horto.backend.core.entities.Subcategory;
import com.horto.backend.core.exceptions.product.ProductNotFoundException;
import com.horto.backend.core.gateway.SubcategoryGateway;

public class GetSubcategoryByNameCaseImpl implements GetSubcategoryByNameCase {

    private final SubcategoryGateway subcategoryGateway;

    public GetSubcategoryByNameCaseImpl(SubcategoryGateway subcategoryGateway) {
        this.subcategoryGateway = subcategoryGateway;
    }

    @Override
    public Subcategory execute(String name) {
        return subcategoryGateway.getSubcategoryByName(name)
                .orElseThrow(() -> new ProductNotFoundException(name));
    }
}
