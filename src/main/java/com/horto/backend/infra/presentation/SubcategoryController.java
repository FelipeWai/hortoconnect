package com.horto.backend.infra.presentation;

import com.horto.backend.core.entities.Subcategory;
import com.horto.backend.core.usecases.subcategory.delete.DeleteSubcategoryByIdCase;
import com.horto.backend.core.usecases.subcategory.get.GetAllSubcategoriesCase;
import com.horto.backend.core.usecases.subcategory.get.GetSubcatByCatIdCase;
import com.horto.backend.core.usecases.subcategory.get.GetSubcatByNameContainingCase;
import com.horto.backend.core.usecases.subcategory.get.GetSubcategoryByIdCase;
import com.horto.backend.core.usecases.subcategory.patch.PatchSubcategoryByIdCase;
import com.horto.backend.core.usecases.subcategory.post.CreateSubcategoryCase;
import com.horto.backend.infra.dto.subcategory.request.SubcategoryPatchDTO;
import com.horto.backend.infra.dto.subcategory.request.SubcategoryRequestDTO;
import com.horto.backend.infra.dto.subcategory.response.SubcategoryResponseDTO;
import com.horto.backend.infra.mapper.SubcategoryMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subcategory")
@RequiredArgsConstructor
public class SubcategoryController {

    private final DeleteSubcategoryByIdCase deleteSubcategoryByIdCase;

    private final PatchSubcategoryByIdCase patchSubcategoryByIdCase;

    private final CreateSubcategoryCase createSubcategoryCase;

    private final GetAllSubcategoriesCase getAllSubcategoriesCase;
    private final GetSubcatByNameContainingCase getSubcatByNameContainingCase;
    private final GetSubcatByCatIdCase getSubcatByCatIdCase;
    private final GetSubcategoryByIdCase getSubcategoryByIdCase;

    private final SubcategoryMapper subcategoryMapper;


    @GetMapping
    public ResponseEntity<List<SubcategoryResponseDTO>> getAllSubcategories(
            @RequestParam(required = false) String name) {

        List<Subcategory> subcategoryList;

        if (name != null && !name.isEmpty()) {
            subcategoryList = getSubcatByNameContainingCase.execute(name);
        } else {
            subcategoryList = getAllSubcategoriesCase.execute();
        }

        return ResponseEntity.ok(subcategoryList.stream()
                .map(subcategoryMapper::toResponseDTO)
                .collect(Collectors.toList())
        );
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<SubcategoryResponseDTO>> getAllSubcategoriesByCategoryId(@PathVariable Long id) {
        List<Subcategory> subcategoryList = getSubcatByCatIdCase.execute(id);
        return ResponseEntity.ok(subcategoryList.stream()
                .map(subcategoryMapper::toResponseDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubcategoryResponseDTO> getSubcategoryById(@PathVariable Long id) {
        Subcategory subcategory = getSubcategoryByIdCase.execute(id);
        return ResponseEntity.ok(subcategoryMapper.toResponseDTO(subcategory));
    }

    @PostMapping
    public ResponseEntity<SubcategoryResponseDTO> createSubcategory(@RequestBody @Valid SubcategoryRequestDTO subcategoryRequestDTO) {
        Subcategory subcategory = createSubcategoryCase.execute(subcategoryRequestDTO);
        return ResponseEntity.ok(subcategoryMapper.toResponseDTO(subcategory));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SubcategoryResponseDTO> patchSubcategory(
            @PathVariable Long id,
            @RequestBody @Valid SubcategoryPatchDTO patchDTO) {
        Subcategory updatedSubcategory = patchSubcategoryByIdCase.execute(id, patchDTO);
        return ResponseEntity.ok(subcategoryMapper.toResponseDTO(updatedSubcategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubcategory(@PathVariable Long id) {
        deleteSubcategoryByIdCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}
