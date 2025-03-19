package com.horto.backend.infra.config.usecases;

import com.horto.backend.core.usecases.quality.get.*;
import com.horto.backend.core.usecases.quality.post.CreateQualityCase;
import com.horto.backend.core.usecases.quality.post.CreateQualityCaseImpl;
import com.horto.backend.infra.gateway.QualityRepoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class QualityUseCasesConfig {

    private final QualityRepoGateway qualityRepoGateway;

    @Bean
    public GetAllQualitiesCase getAllQualitiesCase() {
        return new GetAllQualitiesCaseImpl(qualityRepoGateway);
    }

    @Bean
    public GetQualityByIdCase getQualityByIdCase() {
        return new GetQualityByIdCaseImpl(qualityRepoGateway);
    }

    @Bean
    public CreateQualityCase createQualityCase() {
        return new CreateQualityCaseImpl(qualityRepoGateway);
    }

    @Bean
    public GetQualityByNameCase getQualityByNameCase() {
        return new GetQualityByNameCaseImpl(qualityRepoGateway);
    }

    @Bean
    public GetAllQualitiesByIdCase getAllQualitiesByIdCase() {
        return new GetAllQualitiesByIdCaseImpl(qualityRepoGateway);
    }

}
