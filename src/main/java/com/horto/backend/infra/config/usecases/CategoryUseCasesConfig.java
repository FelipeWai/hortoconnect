package com.horto.backend.infra.config.usecases;

import com.horto.backend.core.usecases.category.delete.DeleteCategoryCase;
import com.horto.backend.core.usecases.category.delete.DeleteCategoryCaseImpl;
import com.horto.backend.core.usecases.category.get.*;
import com.horto.backend.core.usecases.category.post.CreateCategoryCase;
import com.horto.backend.core.usecases.category.post.CreateCategoryCaseImpl;
import com.horto.backend.infra.gateway.CategoryRepoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CategoryUseCasesConfig {

    private final CategoryRepoGateway categoryRepoGateway;

    @Bean
    public GetAllCategoriesCase getAllCategoriesCase() {
        return new GetAllCategoriesCaseImpl(categoryRepoGateway);
    }

    @Bean
    public GetCategoryByIdCase getCategoryByIdCase() {
        return new GetCategoryByIdCaseImpl(categoryRepoGateway);
    }

    @Bean
    public CreateCategoryCase createCategoryCase() {
        return new CreateCategoryCaseImpl(categoryRepoGateway);
    }

    @Bean
    public GetCategoryByNameCase getCategoryByNameCase() {
        return new GetCategoryByNameCaseImpl(categoryRepoGateway);
    }

    @Bean
    public DeleteCategoryCase deleteCategoryCase() {
        return new DeleteCategoryCaseImpl(categoryRepoGateway);
    }

}
