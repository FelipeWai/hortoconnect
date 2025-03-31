package com.horto.backend.infra.config.usecases;

import com.horto.backend.core.usecases.supplierOffer.get.GetOffersByProductCase;
import com.horto.backend.core.usecases.supplierOffer.get.GetOffersByProductCaseImpl;
import com.horto.backend.core.usecases.supplierOffer.get.GetOffersBySupplierIdCase;
import com.horto.backend.core.usecases.supplierOffer.get.GetOffersBySupplierIdCaseImpl;
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

}
