package com.horto.backend.infra.config.usecases;

import com.horto.backend.core.usecases.subcategory.delete.DeleteSubcategoryByIdCase;
import com.horto.backend.core.usecases.subcategory.delete.DeleteSubcategoryByIdCaseImpl;
import com.horto.backend.core.usecases.subcategory.get.*;
import com.horto.backend.core.usecases.subcategory.patch.PatchSubcategoryByIdCase;
import com.horto.backend.core.usecases.subcategory.patch.PatchSubcategoryByIdCaseImpl;
import com.horto.backend.core.usecases.subcategory.post.CreateSubcategoryCase;
import com.horto.backend.core.usecases.subcategory.post.CreateSubcategoryCaseImpl;
import com.horto.backend.infra.gateway.SubcategoryRepoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SubcategoryUseCasesConfig {

    private final SubcategoryRepoGateway subcategoryRepoGateway;

    @Bean
    public GetAllSubcategoriesCase getAllSubcategoriesCase() {
        return new GetAllSubcategoriesCaseImpl(subcategoryRepoGateway);
    }

    @Bean
    public GetSubcategoryByIdCase getSubcategoryByIdCase() {
        return new GetSubcategoryByIdCaseImpl(subcategoryRepoGateway);
    }

    @Bean
    public CreateSubcategoryCase createSubcategoryCase() {
        return new CreateSubcategoryCaseImpl(subcategoryRepoGateway);
    }

    @Bean
    public PatchSubcategoryByIdCase patchSubcategoryByIdCase() {
        return new PatchSubcategoryByIdCaseImpl(subcategoryRepoGateway);
    }

    @Bean
    public DeleteSubcategoryByIdCase deleteSubcategoryByIdCase() {
        return new DeleteSubcategoryByIdCaseImpl(subcategoryRepoGateway);
    }

    @Bean
    public GetSubcategoryByNameCase getSubcategoryByNameCase() {
        return new GetSubcategoryByNameCaseImpl(subcategoryRepoGateway);
    }

}
