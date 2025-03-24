package com.horto.backend.infra.config.usecases;

import com.horto.backend.core.usecases.product.delete.DeleteProductByIdCase;
import com.horto.backend.core.usecases.product.delete.DeleteProductByIdCaseImpl;
import com.horto.backend.core.usecases.product.get.*;
import com.horto.backend.core.usecases.product.patch.PatchProductByIdCase;
import com.horto.backend.core.usecases.product.patch.PatchProductByIdCaseImpl;
import com.horto.backend.core.usecases.product.post.CreateProductCase;
import com.horto.backend.core.usecases.product.post.CreateProductCaseImpl;
import com.horto.backend.infra.gateway.ProductRepoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ProductUseCasesConfig {

    private final ProductRepoGateway productRepoGateway;

    @Bean
    public GetAllProductsCase getAllProductsCase() {
        return new GetAllProductsCaseImpl(productRepoGateway);
    }

    @Bean
    public GetProductByIdCase getProductByIdCase() {
        return new GetProductByIdCaseImpl(productRepoGateway);
    }

    @Bean
    public GetProductByNameCase getProductByNameCase() {
        return new GetProductByNameCaseImpl(productRepoGateway);
    }

    @Bean
    public CreateProductCase createProductCase() {
        return new CreateProductCaseImpl(productRepoGateway);
    }

    @Bean
    public DeleteProductByIdCase deleteProductByIdCase() {
        return new DeleteProductByIdCaseImpl(productRepoGateway);
    }

    @Bean
    public PatchProductByIdCase patchProductByIdCase() {
        return new PatchProductByIdCaseImpl(productRepoGateway);
    }

    @Bean
    public AllProductsByNameContainingCase allProductsByNameContainingCase() {
        return new AllProductsByNameContainingCaseImpl(productRepoGateway);
    }

}
