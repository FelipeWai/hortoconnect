package com.horto.backend.core.usecases.subcategory.post;

import com.horto.backend.core.entities.Subcategory;
import com.horto.backend.core.gateway.SubcategoryGateway;
import com.horto.backend.infra.dto.subcategory.request.SubcategoryRequestDTO;

public class CreateSubcategoryCaseImpl implements CreateSubcategoryCase {

    private final SubcategoryGateway subcategoryGateway;

    public CreateSubcategoryCaseImpl(SubcategoryGateway subcategoryGateway) {
        this.subcategoryGateway = subcategoryGateway;
    }

    @Override
    public Subcategory execute(SubcategoryRequestDTO subcategory) {
        return subcategoryGateway.crateSubcategory(subcategory);
    }
}
