package com.horto.backend.core.usecases.category.post;

import com.horto.backend.core.entities.Category;
import com.horto.backend.core.gateway.CategoryGateway;
import com.horto.backend.infra.dto.category.request.CategoryRequestDTO;

public class CreateCategoryCaseImpl implements CreateCategoryCase {

    private final CategoryGateway categoryGateway;

    public CreateCategoryCaseImpl(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public Category execute(CategoryRequestDTO categoryRequestDTO) {
        return categoryGateway.createCategory(categoryRequestDTO);
    }
}
