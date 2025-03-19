package com.horto.backend.core.usecases.category.delete;

import com.horto.backend.core.gateway.CategoryGateway;

public class DeleteCategoryCaseImpl implements DeleteCategoryCase {

    private final CategoryGateway categoryGateway;

    public DeleteCategoryCaseImpl(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public void execute(Long id) {
        categoryGateway.deleteCategoryById(id);
    }
}
