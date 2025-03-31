package com.horto.backend.core.gateway;

import com.horto.backend.core.entities.Supplier;
import com.horto.backend.infra.dto.supplier.request.SupplierPatchDTO;
import com.horto.backend.infra.dto.supplier.request.SupplierRequestDTO;
import com.horto.backend.infra.filters.supplier.SupplierFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SupplierGateway {

    Page<Supplier> getAllSuppliers(SupplierFilter supplierFilter, Pageable pageable);

    Optional<Supplier> getSupplierById(Long id);

    Optional<Supplier> getSupplierByCnpj(String cnpj);

    Optional<Supplier> getSupplierByName(String supplierName);

    void deleteSupplierById(Long id);

    Supplier patchSupplierById(Long id, SupplierPatchDTO patchDTO);

    Supplier createSupplier(SupplierRequestDTO requestDTO);

}
