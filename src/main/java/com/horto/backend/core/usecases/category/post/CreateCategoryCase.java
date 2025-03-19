package com.horto.backend.core.usecases.category.post;

import com.horto.backend.core.entities.Category;
import com.horto.backend.infra.dto.category.request.CategoryRequestDTO;

public interface CreateCategoryCase {

    Category execute(CategoryRequestDTO categoryRequestDTO);
}
