package com.horto.backend.core.usecases.subcategory.patch;

import com.horto.backend.core.entities.Subcategory;
import com.horto.backend.core.gateway.SubcategoryGateway;
import com.horto.backend.infra.dto.subcategory.request.SubcategoryPatchDTO;

public class PatchSubcategoryByIdCaseImpl implements PatchSubcategoryByIdCase {

    private final SubcategoryGateway subcategoryGateway;

    public PatchSubcategoryByIdCaseImpl(SubcategoryGateway subcategoryGateway) {
        this.subcategoryGateway = subcategoryGateway;
    }

    @Override
    public Subcategory execute(Long id, SubcategoryPatchDTO patchDTO) {
        return subcategoryGateway.patchSubcategoryById(id, patchDTO);
    }
}
