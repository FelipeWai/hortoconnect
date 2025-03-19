package com.horto.backend.core.usecases.quality.post;

import com.horto.backend.core.entities.Quality;
import com.horto.backend.core.gateway.QualityGateway;
import com.horto.backend.infra.dto.quality.request.QualityRequestDTO;

public class CreateQualityCaseImpl implements CreateQualityCase {

    private final QualityGateway qualityGateway;

    public CreateQualityCaseImpl(QualityGateway qualityGateway) {
        this.qualityGateway = qualityGateway;
    }

    @Override
    public Quality createQuality(QualityRequestDTO quality) {
        return qualityGateway.createQuality(quality);
    }
}
