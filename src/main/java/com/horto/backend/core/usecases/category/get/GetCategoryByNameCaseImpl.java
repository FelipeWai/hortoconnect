package com.horto.backend.core.usecases.category.get;

import com.horto.backend.core.entities.Category;
import com.horto.backend.core.exceptions.category.CategoryAlreadyExists;
import com.horto.backend.core.exceptions.category.CategoryNotFoundException;
import com.horto.backend.core.gateway.CategoryGateway;

public class GetCategoryByNameCaseImpl implements GetCategoryByNameCase {

    private final CategoryGateway categoryGateway;

    public GetCategoryByNameCaseImpl(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public Category execute(String name) {
        return categoryGateway.getCategoryByName(name)
                .orElseThrow(() -> new CategoryNotFoundException(name));
    }
}
