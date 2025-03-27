package com.horto.backend.infra.config.usecases;

import com.horto.backend.core.usecases.suppliers.delete.DeleteSupplierCase;
import com.horto.backend.core.usecases.suppliers.delete.DeleteSupplierCaseImpl;
import com.horto.backend.core.usecases.suppliers.get.*;
import com.horto.backend.core.usecases.suppliers.patch.PatchSupplierCase;
import com.horto.backend.core.usecases.suppliers.patch.PatchSupplierCaseImpl;
import com.horto.backend.core.usecases.suppliers.post.CreateSupplierCase;
import com.horto.backend.core.usecases.suppliers.post.CreateSupplierCaseImpl;
import com.horto.backend.infra.gateway.SupplierRepoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SupplierUseCasesConfig {

    private final SupplierRepoGateway supplierRepoGateway;

    @Bean
    public GetAllSuppliersCase getAllSuppliersCase() {
        return new GetAllSuppliersCaseImpl(supplierRepoGateway);
    }

    @Bean
    public GetSupplierByIdCase getSupplierByIdCase() {
        return new GetSupplierByIdCaseImpl(supplierRepoGateway);
    }

    @Bean
    public GetSupplierByNameCase getSupplierByNameCase() {
        return new GetSupplierByNameCaseImpl(supplierRepoGateway);
    }

    @Bean
    public GetSupplierByCnpjCase getSupplierByCnpjCase() {
        return new GetSupplierByCnpjCaseImpl(supplierRepoGateway);
    }

    @Bean
    public CreateSupplierCase createSupplierCase() {
        return new CreateSupplierCaseImpl(supplierRepoGateway);
    }

    @Bean
    public DeleteSupplierCase deleteSupplierCase() {
        return new DeleteSupplierCaseImpl(supplierRepoGateway);
    }

    @Bean
    public PatchSupplierCase patchSupplierCase() {
        return new PatchSupplierCaseImpl(supplierRepoGateway);
    }

}
