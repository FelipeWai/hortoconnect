package com.horto.backend.core.usecases.subcategory.patch;

import com.horto.backend.core.entities.Subcategory;
import com.horto.backend.infra.dto.subcategory.request.SubcategoryPatchDTO;

public interface PatchSubcategoryByIdCase {

    Subcategory execute(Long id, SubcategoryPatchDTO patchDTO);

}
