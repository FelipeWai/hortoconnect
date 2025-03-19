package com.horto.backend.core.gateway;

import com.horto.backend.core.entities.Category;
import com.horto.backend.infra.dto.category.request.CategoryRequestDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryGateway {

    List<Category> getAllCategories();

    Optional<Category> getCategoryById(Long id);

    Category createCategory(CategoryRequestDTO categoryRequestDTO);

    Optional<Category> getCategoryByName(String name);

    void deleteCategoryById(Long id);

}
