package com.horto.backend.core.usecases.category.get;

import com.horto.backend.core.entities.Category;

public interface GetCategoryByIdCase {
    Category execute(Long id);
}
