package com.horto.backend.core.usecases.quality.delete;

import com.horto.backend.core.gateway.QualityGateway;

public class DeleteQualityByIdCaseImpl implements DeleteQualityByIdCase {

    private final QualityGateway qualityGateway;

    public DeleteQualityByIdCaseImpl(QualityGateway qualityGateway) {
        this.qualityGateway = qualityGateway;
    }

    @Override
    public void execute(Long id) {
        qualityGateway.deleteQualityById(id);
    }
}
