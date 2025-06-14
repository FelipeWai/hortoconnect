package com.horto.backend.core.gateway;

import com.horto.backend.core.entities.Subcategory;
import com.horto.backend.infra.dto.subcategory.request.SubcategoryPatchDTO;
import com.horto.backend.infra.dto.subcategory.request.SubcategoryRequestDTO;

import java.util.List;
import java.util.Optional;

public interface SubcategoryGateway {

    List<Subcategory> getAllSubcategories();

    List<Subcategory> getAllSubcategoriesByCategoryId(Long categoryId);

    List<Subcategory> getSubcategoriesByNameContaining(String nameFragment);

    Optional<Subcategory> getSubcategoryById(Long id);

    Optional<Subcategory> getSubcategoryByName(String name);

    Subcategory crateSubcategory(SubcategoryRequestDTO subcategory);

    Subcategory patchSubcategoryById(Long id, SubcategoryPatchDTO patchDTO);

    void deleteSubcategoryById(Long id);

}
