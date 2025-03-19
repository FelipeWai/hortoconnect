package com.horto.backend.infra.gateway;

import com.horto.backend.core.entities.Quality;
import com.horto.backend.core.exceptions.category.CategoryAlreadyExists;
import com.horto.backend.core.exceptions.quality.QualityAlreadyExists;
import com.horto.backend.core.gateway.QualityGateway;
import com.horto.backend.infra.dto.quality.request.QualityRequestDTO;
import com.horto.backend.infra.mapper.QualityMapper;
import com.horto.backend.infra.persistence.entities.QualityEntity;
import com.horto.backend.infra.persistence.repositories.QualityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class QualityRepoGateway implements QualityGateway {

    private final QualityRepository qualityRepository;

    private final QualityMapper qualityMapper;

    @Override
    public List<Quality> getAllQualities() {
        List<QualityEntity> entityList = qualityRepository.findAll();
        return entityList.stream()
                .map(qualityMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Quality> getQualityById(Long id) {
        Optional<QualityEntity> entityOptional = qualityRepository.findById(id);
        if (entityOptional.isPresent()) {
            return Optional.of(qualityMapper.toDomain(entityOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Quality> getQualityByName(String name) {
        Optional<QualityEntity> qualityEntityOptional = qualityRepository.findByName(name);
        if (qualityEntityOptional.isPresent()) {
            return Optional.of(qualityMapper.toDomain(qualityEntityOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public Quality createQuality(QualityRequestDTO quality) {
        String requestNameToLowerCase = quality.name().trim().toLowerCase();

        if (getQualityByName(requestNameToLowerCase).isPresent()) {
            throw new QualityAlreadyExists(quality.name());
        }


        QualityEntity entity = qualityMapper.toEntity(quality);
        entity.setName(requestNameToLowerCase);
        QualityEntity savedEntity = qualityRepository.save(entity);
        return qualityMapper.toDomain(savedEntity);
    }
}
