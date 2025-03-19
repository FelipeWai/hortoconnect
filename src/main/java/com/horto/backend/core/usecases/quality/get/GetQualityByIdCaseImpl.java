package com.horto.backend.core.usecases.quality.get;

import com.horto.backend.core.entities.Quality;
import com.horto.backend.core.exceptions.quality.QualityNotFoundException;
import com.horto.backend.core.gateway.QualityGateway;

public class GetQualityByIdCaseImpl implements GetQualityByIdCase {

    private final QualityGateway qualityGateway;

    public GetQualityByIdCaseImpl(QualityGateway qualityGateway) {
        this.qualityGateway = qualityGateway;
    }

    @Override
    public Quality execute(Long id) {
        return qualityGateway.getQualityById(id)
                .orElseThrow(() -> new QualityNotFoundException(id.toString()));
    }
}
