package com.horto.backend.infra.gateway;

import com.horto.backend.core.entities.Category;
import com.horto.backend.core.entities.Quality;
import com.horto.backend.core.entities.Size;
import com.horto.backend.core.entities.Subcategory;
import com.horto.backend.core.exceptions.category.CategoryNotFoundException;
import com.horto.backend.core.exceptions.quality.QualityNotFoundException;
import com.horto.backend.core.exceptions.size.SizeNotFoundException;
import com.horto.backend.core.exceptions.subcategory.SubcategoryAlreadyExistsException;
import com.horto.backend.core.exceptions.subcategory.SubcategoryNotFoundException;
import com.horto.backend.core.gateway.SubcategoryGateway;
import com.horto.backend.core.usecases.category.get.GetCategoryByIdCase;
import com.horto.backend.core.usecases.quality.get.GetAllQualitiesByIdCase;
import com.horto.backend.core.usecases.quality.get.GetQualityByIdCase;
import com.horto.backend.core.usecases.size.get.GetAllSizesByIdCase;
import com.horto.backend.core.usecases.size.get.GetSizeByIdCase;
import com.horto.backend.core.usecases.subcategory.get.GetSubcategoryByIdCase;
import com.horto.backend.infra.dto.subcategory.request.SubcategoryPatchDTO;
import com.horto.backend.infra.dto.subcategory.request.SubcategoryRequestDTO;
import com.horto.backend.infra.mapper.CategoryMapper;
import com.horto.backend.infra.mapper.QualityMapper;
import com.horto.backend.infra.mapper.SizeMapper;
import com.horto.backend.infra.mapper.SubcategoryMapper;
import com.horto.backend.infra.persistence.entities.QualityEntity;
import com.horto.backend.infra.persistence.entities.SizeEntity;
import com.horto.backend.infra.persistence.entities.SubcategoryEntity;
import com.horto.backend.infra.persistence.repositories.SubcategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SubcategoryRepoGateway implements SubcategoryGateway {

    private final GetCategoryByIdCase getCategoryByIdCase;


    private final GetSizeByIdCase getSizeByIdCase;
    private final GetAllSizesByIdCase getAllSizesByIdCase;

    private final GetQualityByIdCase getQualityByIdCase;
    private final GetAllQualitiesByIdCase getAllQualitiesByIdCase;

    private final SubcategoryRepository subcategoryRepository;

    private final SubcategoryMapper subcategoryMapper;
    private final SizeMapper sizeMapper;
    private final QualityMapper qualityMapper;
    private final CategoryMapper categoryMapper;


    @Override
    public List<Subcategory> getAllSubcategories() {
        List<SubcategoryEntity> subcategoryEntityList = subcategoryRepository.findAll();
        return subcategoryEntityList.stream()
                .map(subcategoryMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Subcategory> getSubcategoryById(Long id) {
        Optional<SubcategoryEntity> entityOptional = subcategoryRepository.findById(id);
        if (entityOptional.isPresent()) {
            return Optional.of(subcategoryMapper.toDomain(entityOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Subcategory> getSubcategoryByName(String name) {
        Optional<SubcategoryEntity> entityOptional = subcategoryRepository.findByName(name);
        if (entityOptional.isPresent()) {
            return Optional.of(subcategoryMapper.toDomain(entityOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public Subcategory crateSubcategory(SubcategoryRequestDTO subcategory) {
        if(getSubcategoryByName(subcategory.name()).isPresent()){
            throw new SubcategoryAlreadyExistsException(subcategory.name());
        }

        List<Quality> qualityList = subcategory.qualities_id().stream()
                .map(getQualityByIdCase::execute)
                .collect(Collectors.toList());

        List<Size> sizeList = subcategory.sizes_id().stream()
                .map(getSizeByIdCase::execute)
                .collect(Collectors.toList());

        Category category = getCategoryByIdCase.execute(subcategory.category_id());

        SubcategoryEntity entity = subcategoryMapper.toEntity(subcategory);

        entity.setSizes(sizeList.stream()
                .map(sizeMapper::toEntity)
                .toList());

        entity.setQualities(qualityList.stream()
                .map(qualityMapper::toEntity)
                .toList());

        entity.setCategory(categoryMapper.toEntity(category));

        entity = subcategoryRepository.save(entity);

        return subcategoryMapper.toDomain(entity);
    }

    @Override
    public Subcategory patchSubcategoryById(Long id, SubcategoryPatchDTO patchDTO) {
        Subcategory subcategoryToUpdate = getSubcategoryById(id)
                .orElseThrow(() -> new SubcategoryNotFoundException(id.toString()));

        SubcategoryEntity subcategoryEntityToUpdate = subcategoryMapper.toEntity(subcategoryToUpdate);

        boolean hasChanges = false;

        if (patchDTO.name().isPresent()) {
            String newName = patchDTO.name().get();
            if (!newName.equals(subcategoryEntityToUpdate.getName())) {
                subcategoryEntityToUpdate.setName(newName);
                hasChanges = true;
            }
        }

        if (patchDTO.category_id().isPresent()) {
            Long newCategoryId = patchDTO.category_id().get();
            if (!newCategoryId.equals(subcategoryEntityToUpdate.getCategory().getId())) {
                Category category = getCategoryByIdCase.execute(newCategoryId);
                subcategoryEntityToUpdate.setCategory(categoryMapper.toEntity(category));
                hasChanges = true;
            }
        }

        if (patchDTO.qualities_id().isPresent()) {
            List<Long> newQualitiesIds = patchDTO.qualities_id().get();
            Set<Long> currentQualityIds = subcategoryEntityToUpdate.getQualities().stream()
                    .map(QualityEntity::getId)
                    .collect(Collectors.toSet());
            Set<Long> newQualityIdsSet = new HashSet<>(newQualitiesIds);

            if (!currentQualityIds.equals(newQualityIdsSet)) {
                List<Quality> qualities = getAllQualitiesByIdCase.execute(newQualitiesIds);
                if (qualities.size() != newQualitiesIds.size()) {
                    throw new QualityNotFoundException(newQualitiesIds.toString());
                }
                subcategoryEntityToUpdate.setQualities(qualities.stream()
                        .map(qualityMapper::toEntity)
                        .collect(Collectors.toList()));
                hasChanges = true;
            }
        }

        if (patchDTO.sizes_id().isPresent()) {
            List<Long> newSizesIds = patchDTO.sizes_id().get();
            Set<Long> currentSizeIds = subcategoryEntityToUpdate.getSizes().stream()
                    .map(SizeEntity::getId)
                    .collect(Collectors.toSet());
            Set<Long> newSizeIdsSet = new HashSet<>(newSizesIds);

            if (!currentSizeIds.equals(newSizeIdsSet)) {
                List<Size> sizes = getAllSizesByIdCase.execute(newSizesIds);
                if (sizes.size() != newSizesIds.size()) {
                    throw new SizeNotFoundException(newSizesIds.toString());
                }
                subcategoryEntityToUpdate.setSizes(sizes.stream()
                        .map(sizeMapper::toEntity)
                        .collect(Collectors.toList()));
                hasChanges = true;
            }
        }

        if (hasChanges) {
            SubcategoryEntity savedEntity = subcategoryRepository.save(subcategoryEntityToUpdate);
            return subcategoryMapper.toDomain(savedEntity);
        } else {
            return subcategoryToUpdate;
        }
    }

    @Transactional
    @Override
    public void deleteSubcategoryById(Long id) {
        if(getSubcategoryById(id).isPresent()) {
            subcategoryRepository.deleteById(id);
        }else{
            throw new SubcategoryNotFoundException(id.toString());
        }
    }
}
