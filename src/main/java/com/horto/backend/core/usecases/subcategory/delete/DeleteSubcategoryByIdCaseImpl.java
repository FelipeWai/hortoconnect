package com.horto.backend.core.usecases.subcategory.delete;

import com.horto.backend.core.gateway.SubcategoryGateway;

public class DeleteSubcategoryByIdCaseImpl implements DeleteSubcategoryByIdCase {

    private final SubcategoryGateway subcategoryGateway;

    public DeleteSubcategoryByIdCaseImpl(SubcategoryGateway subcategoryGateway) {
        this.subcategoryGateway = subcategoryGateway;
    }

    @Override
    public void execute(Long id) {
        subcategoryGateway.deleteSubcategoryById(id);
    }
}
