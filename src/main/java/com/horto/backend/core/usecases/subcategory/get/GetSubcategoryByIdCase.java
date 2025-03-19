package com.horto.backend.core.usecases.subcategory.get;

import com.horto.backend.core.entities.Subcategory;

public interface GetSubcategoryByIdCase {
    Subcategory execute(Long id);
}
