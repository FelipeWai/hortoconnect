package com.horto.backend.core.usecases.subcategory.get;

import com.horto.backend.core.entities.Subcategory;

public interface GetSubcategoryByNameCase {

    Subcategory execute(String name);

}
