package com.horto.backend.infra.presentation;

import com.horto.backend.core.entities.Category;
import com.horto.backend.core.usecases.category.delete.DeleteCategoryCase;
import com.horto.backend.core.usecases.category.get.GetAllCategoriesCase;
import com.horto.backend.core.usecases.category.get.GetCategoryByIdCase;
import com.horto.backend.core.usecases.category.get.GetCategoryByNameCase;
import com.horto.backend.core.usecases.category.post.CreateCategoryCase;
import com.horto.backend.infra.dto.category.request.CategoryRequestDTO;
import com.horto.backend.infra.dto.category.response.CategoryResponseDTO;
import com.horto.backend.infra.mapper.CategoryMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final DeleteCategoryCase deleteCategoryCase;

    private final CreateCategoryCase createCategoryCase;

    private final GetAllCategoriesCase getAllCategoriesCase;
    private final GetCategoryByIdCase getCategoryByIdCase;
    private final GetCategoryByNameCase getCategoryByNameCase;

    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<Category> categories = getAllCategoriesCase.execute();
        return ResponseEntity.ok(categories.stream()
                .map(categoryMapper::toResponseDTO)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        Category category = getCategoryByIdCase.execute(id);
        return ResponseEntity.ok(categoryMapper.toResponseDTO(category));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CategoryResponseDTO> getCategoryByName(@PathVariable String name) {
        Category category = getCategoryByNameCase.execute(name);
        return ResponseEntity.ok(categoryMapper.toResponseDTO(category));
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody @Valid CategoryRequestDTO categoryRequestDTO) {
        Category newCategory = createCategoryCase.execute(categoryRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryMapper.toResponseDTO(newCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        deleteCategoryCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
