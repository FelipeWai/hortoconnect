package com.horto.backend.core.usecases.category.get;

import com.horto.backend.core.entities.Category;
import com.horto.backend.core.exceptions.category.CategoryNotFoundException;
import com.horto.backend.core.gateway.CategoryGateway;

public class GetCategoryByIdCaseImpl implements GetCategoryByIdCase {

    private final CategoryGateway categoryGateway;

    public GetCategoryByIdCaseImpl(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public Category execute(Long id){
        return categoryGateway.getCategoryById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id.toString()));
    }
}
