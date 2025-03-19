package com.horto.backend.infra.mapper;

import com.horto.backend.core.entities.Quality;
import com.horto.backend.infra.dto.quality.request.QualityRequestDTO;
import com.horto.backend.infra.dto.quality.response.QualityResponseDTO;
import com.horto.backend.infra.persistence.entities.QualityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QualityMapper {

    Quality toDomain(QualityEntity entity);

    QualityResponseDTO toResponseDTO(Quality quality);

    QualityEntity toEntity(QualityRequestDTO qualityRequestDTO);
    QualityEntity toEntity(Quality quality);
}
