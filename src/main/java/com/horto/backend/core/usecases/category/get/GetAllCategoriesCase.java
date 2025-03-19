package com.horto.backend.core.usecases.category.get;

import com.horto.backend.core.entities.Category;

import java.util.List;

public interface GetAllCategoriesCase {

    List<Category> execute();

}
