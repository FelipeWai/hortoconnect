package com.horto.backend.core.gateway;

import com.horto.backend.core.entities.Quality;
import com.horto.backend.infra.dto.quality.request.QualityRequestDTO;

import java.util.List;
import java.util.Optional;

public interface QualityGateway {

    List<Quality> getAllQualities();

    List<Quality> getAllQualitiesById(List<Long> ids);

    Optional<Quality> getQualityById(Long id);

    Optional<Quality> getQualityByName(String name);

    Quality createQuality(QualityRequestDTO quality);

    void deleteQualityById(Long id);

}
