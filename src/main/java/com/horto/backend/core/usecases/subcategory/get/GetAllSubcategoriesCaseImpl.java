package com.horto.backend.core.usecases.subcategory.get;

import com.horto.backend.core.entities.Subcategory;
import com.horto.backend.core.gateway.SubcategoryGateway;

import java.util.List;

public class GetAllSubcategoriesCaseImpl implements GetAllSubcategoriesCase {

    private final SubcategoryGateway subcategoryGateway;

    public GetAllSubcategoriesCaseImpl(SubcategoryGateway subcategoryGateway) {
        this.subcategoryGateway = subcategoryGateway;
    }

    @Override
    public List<Subcategory> execute() {
        return subcategoryGateway.getAllSubcategories();
    }
}
