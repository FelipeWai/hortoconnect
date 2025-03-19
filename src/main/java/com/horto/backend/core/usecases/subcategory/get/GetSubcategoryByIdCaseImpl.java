package com.horto.backend.core.usecases.subcategory.get;

import com.horto.backend.core.entities.Subcategory;
import com.horto.backend.core.exceptions.subcategory.SubcategoryNotFoundException;
import com.horto.backend.core.gateway.SubcategoryGateway;

public class GetSubcategoryByIdCaseImpl implements GetSubcategoryByIdCase {

    private final SubcategoryGateway subcategoryGateway;

    public GetSubcategoryByIdCaseImpl(SubcategoryGateway subcategoryGateway) {
        this.subcategoryGateway = subcategoryGateway;
    }

    @Override
    public Subcategory execute(Long id) {
        return subcategoryGateway.getSubcategoryById(id)
                .orElseThrow(() -> new SubcategoryNotFoundException(id.toString()));
    }
}
