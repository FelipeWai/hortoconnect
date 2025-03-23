package com.horto.backend.core.usecases.subcategory.get;

import com.horto.backend.core.entities.Subcategory;
import com.horto.backend.core.gateway.SubcategoryGateway;

import java.util.List;

public class GetSubcatByNameContainingCaseImpl implements GetSubcatByNameContainingCase {

    private final SubcategoryGateway subcategoryGateway;

    public GetSubcatByNameContainingCaseImpl(SubcategoryGateway subcategoryGateway) {
        this.subcategoryGateway = subcategoryGateway;
    }

    @Override
    public List<Subcategory> execute(String nameFragment) {
        return subcategoryGateway.getSubcategoriesByNameContaining(nameFragment);
    }
}
