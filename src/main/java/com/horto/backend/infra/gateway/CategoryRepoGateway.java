package com.horto.backend.infra.gateway;

import com.horto.backend.core.entities.Category;
import com.horto.backend.core.exceptions.category.CategoryAlreadyExists;
import com.horto.backend.core.exceptions.category.CategoryNotFoundException;
import com.horto.backend.core.gateway.CategoryGateway;
import com.horto.backend.infra.dto.category.request.CategoryRequestDTO;
import com.horto.backend.infra.mapper.CategoryMapper;
import com.horto.backend.infra.persistence.entities.CategoryEntity;
import com.horto.backend.infra.persistence.repositories.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryRepoGateway implements CategoryGateway {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategories() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        return categoryEntityList.stream()
                .map(categoryMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Category> getCategoryById(Long id) {
        Optional<CategoryEntity> entity = categoryRepository.findById(id);
        if (entity.isPresent()) {
            return Optional.of(categoryMapper.toDomain(entity.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Category> getCategoryByName(String name) {
        Optional<CategoryEntity> entity = categoryRepository.findByName(name);
        if (entity.isPresent()) {
            return Optional.of(categoryMapper.toDomain(entity.get()));
        }
        return Optional.empty();
    }

    @Override
    public Category createCategory(CategoryRequestDTO categoryRequestDTO) {
        if (getCategoryByName(categoryRequestDTO.name().trim().toLowerCase()).isPresent()) {
            throw new CategoryAlreadyExists(categoryRequestDTO.name());
        }

        CategoryEntity categoryEntity = categoryMapper.toEntity(categoryRequestDTO);
        categoryEntity.setName(categoryRequestDTO.name().trim().toLowerCase());
        CategoryEntity savedCategoryEntity = categoryRepository.save(categoryEntity);
        return categoryMapper.toDomain(savedCategoryEntity);
    }

    @Transactional
    @Override
    public void deleteCategoryById(Long id) {
        getCategoryById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id.toString()));

        categoryRepository.deleteById(id);
    }
}
