package com.horto.backend.core.usecases.quality.get;

import com.horto.backend.core.entities.Quality;
import com.horto.backend.core.gateway.QualityGateway;

import java.util.List;

public class GetAllQualitiesCaseImpl implements GetAllQualitiesCase {

    private final QualityGateway qualityGateway;

    public GetAllQualitiesCaseImpl(QualityGateway qualityGateway) {
        this.qualityGateway = qualityGateway;
    }

    @Override
    public List<Quality> execute() {
        return qualityGateway.getAllQualities();
    }
}
