package com.horto.backend.core.usecases.subcategory.post;

import com.horto.backend.core.entities.Subcategory;
import com.horto.backend.infra.dto.subcategory.request.SubcategoryRequestDTO;

public interface CreateSubcategoryCase {

    Subcategory execute(SubcategoryRequestDTO subcategory);

}
