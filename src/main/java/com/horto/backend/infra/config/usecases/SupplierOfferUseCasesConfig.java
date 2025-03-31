package com.horto.backend.infra.config.usecases;

import com.horto.backend.core.usecases.supplierOffer.delete.DeleteOfferByIdCase;
import com.horto.backend.core.usecases.supplierOffer.delete.DeleteOfferByIdCaseImpl;
import com.horto.backend.core.usecases.supplierOffer.get.*;
import com.horto.backend.core.usecases.supplierOffer.patch.PatchOfferByIdCase;
import com.horto.backend.core.usecases.supplierOffer.patch.PatchOfferByIdCaseImpl;
import com.horto.backend.core.usecases.supplierOffer.post.CreateOfferCase;
import com.horto.backend.core.usecases.supplierOffer.post.CreateOfferCaseImpl;
import com.horto.backend.infra.gateway.SupplierOfferRepoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SupplierOfferUseCasesConfig {

    private final SupplierOfferRepoGateway supplierOfferRepoGateway;

    @Bean
    public GetOffersBySupplierIdCase getOffersBySupplierIdCase() {
        return new GetOffersBySupplierIdCaseImpl(supplierOfferRepoGateway);
    }

    @Bean
    public CreateOfferCase createOfferCase() {
        return new CreateOfferCaseImpl(supplierOfferRepoGateway);
    }

    @Bean
    public GetOffersByProductCase getOffersByProductCase() {
        return new GetOffersByProductCaseImpl(supplierOfferRepoGateway);
    }

    @Bean
    public GetOffersByProductAndSupplierCase getOffersByProductAndSupplierCase() {
        return new GetOffersByProductAndSupplierCaseImpl(supplierOfferRepoGateway);
    }

    @Bean
    public DeleteOfferByIdCase deleteOfferByIdCase() {
        return new DeleteOfferByIdCaseImpl(supplierOfferRepoGateway);
    }

    @Bean
    public GetOfferByIdCase getOfferByIdCase() {
        return new GetOfferByIdCaseImpl(supplierOfferRepoGateway);
    }

    @Bean
    public PatchOfferByIdCase patchOfferByIdCase() {
        return new PatchOfferByIdCaseImpl(supplierOfferRepoGateway);
    }

}
