package com.horto.backend.infra.gateway;

import com.horto.backend.core.entities.Category;
import com.horto.backend.core.entities.Quality;
import com.horto.backend.core.entities.Size;
import com.horto.backend.core.entities.Subcategory;
import com.horto.backend.core.exceptions.category.CategoryNotFoundException;
import com.horto.backend.core.exceptions.size.SizeNotFoundException;
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
import com.horto.backend.infra.persistence.entities.SubcategoryEntity;
import com.horto.backend.infra.persistence.repositories.SubcategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
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
    public Subcategory crateSubcategory(SubcategoryRequestDTO subcategory) {
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
        Subcategory subcategoryToUpdate = getSubcategoryById(id).orElseThrow(() -> new SizeNotFoundException(id.toString()));
        SubcategoryEntity subcategoryEntityToUpdate = subcategoryMapper.toEntity(subcategoryToUpdate);

        patchDTO.name().ifPresent(subcategoryEntityToUpdate::setName);

        patchDTO.category_id().ifPresent(categoryId ->{
           Category category = getCategoryByIdCase.execute(categoryId);
           subcategoryEntityToUpdate.setCategory(categoryMapper.toEntity(category));
        });

        patchDTO.qualities_id().ifPresent(qualitiesId ->{
            List<Quality> qualities = getAllQualitiesByIdCase.execute(qualitiesId);
            if (qualities.size() != qualitiesId.size()) {
                throw new CategoryNotFoundException(qualitiesId.toString());
            }
            subcategoryEntityToUpdate.setQualities(qualities.stream().map(qualityMapper::toEntity).collect(Collectors.toList()));
        });

        patchDTO.sizes_id().ifPresent(sizesId ->{
            List<Size> sizes = getAllSizesByIdCase.execute(sizesId);
            if (sizes.size() != sizesId.size()) {
                throw new SizeNotFoundException(sizesId.toString());
            }
            subcategoryEntityToUpdate.setSizes(sizes.stream().map(sizeMapper::toEntity).collect(Collectors.toList()));
        });

        SubcategoryEntity savedEntity = subcategoryRepository.save(subcategoryEntityToUpdate);

        return subcategoryMapper.toDomain(savedEntity);
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
