package com.horto.backend.core.usecases.category.get;

import com.horto.backend.core.entities.Category;
import com.horto.backend.core.gateway.CategoryGateway;

import java.util.List;

public class GetAllCategoriesCaseImpl implements GetAllCategoriesCase {

    private final CategoryGateway categoryGateway;

    public GetAllCategoriesCaseImpl(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public List<Category> execute() {
        return categoryGateway.getAllCategories();
    }
}
