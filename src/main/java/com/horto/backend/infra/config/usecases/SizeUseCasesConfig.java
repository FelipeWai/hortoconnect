package com.horto.backend.infra.config.usecases;

import com.horto.backend.core.usecases.size.get.*;
import com.horto.backend.core.usecases.size.post.CreateSizeCase;
import com.horto.backend.core.usecases.size.post.CreateSizeCaseImpl;
import com.horto.backend.infra.gateway.SizeRepoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SizeUseCasesConfig {

    private final SizeRepoGateway sizeRepoGateway;

    @Bean
    public GetAllSizesCase getAllSizesCase() {
        return new GetAllSizesCaseImpl(sizeRepoGateway);
    }

    @Bean
    public GetSizeByIdCase getSizeByIdCase() {
        return new GetSizeByIdCaseImpl(sizeRepoGateway);
    }

    @Bean
    public CreateSizeCase createSizeCase() {
        return new CreateSizeCaseImpl(sizeRepoGateway);
    }

    @Bean
    public GetSizeByNameCase getSizeByNameCase() {
        return new GetSizeByNameCaseImpl(sizeRepoGateway);
    }
}
