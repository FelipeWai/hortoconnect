package com.horto.backend.core.usecases.quality.get;

import com.horto.backend.core.entities.Quality;
import com.horto.backend.core.gateway.QualityGateway;

import java.util.List;

public class GetAllQualitiesByIdCaseImpl implements GetAllQualitiesByIdCase {

    private final QualityGateway qualityGateway;

    public GetAllQualitiesByIdCaseImpl(QualityGateway qualityGateway) {
        this.qualityGateway = qualityGateway;
    }

    @Override
    public List<Quality> execute(List<Long> ids) {
        return qualityGateway.getAllQualitiesById(ids);
    }
}
