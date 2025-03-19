package com.horto.backend.core.usecases.quality.get;

import com.horto.backend.core.entities.Quality;
import com.horto.backend.core.exceptions.quality.QualityAlreadyExists;
import com.horto.backend.core.exceptions.quality.QualityNotFoundException;
import com.horto.backend.core.gateway.QualityGateway;

public class GetQualityByNameCaseImpl implements GetQualityByNameCase{

    private final QualityGateway qualityGateway;

    public GetQualityByNameCaseImpl(QualityGateway qualityGateway) {
        this.qualityGateway = qualityGateway;
    }

    @Override
    public Quality execute(String name) {
        return qualityGateway.getQualityByName(name)
                .orElseThrow(() -> new QualityNotFoundException(name));
    }
}
