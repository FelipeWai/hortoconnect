package com.horto.backend.core.usecases.subcategory.get;

import com.horto.backend.core.entities.Subcategory;
import com.horto.backend.core.gateway.SubcategoryGateway;

import java.util.List;

public class GetSubcatByCatIdCaseImpl implements GetSubcatByCatIdCase {

    private final SubcategoryGateway subcategoryGateway;

    public GetSubcatByCatIdCaseImpl(SubcategoryGateway subcategoryGateway) {
        this.subcategoryGateway = subcategoryGateway;
    }

    @Override
    public List<Subcategory> execute(Long categoryId) {
        return subcategoryGateway.getAllSubcategoriesByCategoryId(categoryId);
    }
}
