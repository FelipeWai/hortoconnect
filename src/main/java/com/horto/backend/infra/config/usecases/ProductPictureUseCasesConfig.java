package com.horto.backend.infra.config.usecases;

import com.horto.backend.core.usecases.productPicture.post.CreateProductPicturesCase;
import com.horto.backend.core.usecases.productPicture.post.CreateProductPicturesCaseImpl;
import com.horto.backend.infra.gateway.ProductPictureRepoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProductPictureUseCasesConfig {

    private final ProductPictureRepoGateway productPictureRepoGateway;

    @Bean
    public CreateProductPicturesCase createProductPictureCase() {
        return new CreateProductPicturesCaseImpl(productPictureRepoGateway);
    }

}
