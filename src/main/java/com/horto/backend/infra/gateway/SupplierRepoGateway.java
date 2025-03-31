package com.horto.backend.infra.gateway;

import com.horto.backend.core.entities.Supplier;
import com.horto.backend.core.exceptions.supplier.SupplierNameAlreadyExistsException;
import com.horto.backend.core.exceptions.supplier.SupplierNotFoundException;
import com.horto.backend.core.exceptions.user.alreadyExists.CnpjAlreadyExistsException;
import com.horto.backend.core.gateway.SupplierGateway;
import com.horto.backend.infra.dto.supplier.request.SupplierPatchDTO;
import com.horto.backend.infra.dto.supplier.request.SupplierRequestDTO;
import com.horto.backend.infra.filters.supplier.SupplierFilter;
import com.horto.backend.infra.filters.supplier.SupplierSpecification;
import com.horto.backend.infra.mapper.SupplierMapper;
import com.horto.backend.infra.persistence.entities.SupplierEntity;
import com.horto.backend.infra.persistence.repositories.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SupplierRepoGateway implements SupplierGateway {

    private final SupplierRepository supplierRepository;

    private final SupplierMapper supplierMapper;

    @Override
    public Page<Supplier> getAllSuppliers(SupplierFilter supplierFilter, Pageable pageable) {
        Specification<SupplierEntity> specification = new SupplierSpecification(supplierFilter);
        Page<SupplierEntity> supplierEntities = supplierRepository.findAll(specification, pageable);
        return supplierEntities.map(supplierMapper::toDomain);
    }

    @Override
    public Optional<Supplier> getSupplierById(Long id) {
        Optional<SupplierEntity> supplierEntity = supplierRepository.findById(id);
        return supplierEntity.map(supplierMapper::toDomain);
    }

    @Override
    public Optional<Supplier> getSupplierByCnpj(String cnpj) {
        Optional<SupplierEntity> supplierEntity = supplierRepository.findByCnpj(cnpj);
        return supplierEntity.map(supplierMapper::toDomain);
    }

    @Override
    public Optional<Supplier> getSupplierByName(String supplierName) {
        Optional<SupplierEntity> supplierEntity = supplierRepository.findByName(supplierName);
        return supplierEntity.map(supplierMapper::toDomain);
    }

    @Override
    public Supplier createSupplier(SupplierRequestDTO requestDTO) {
        if(getSupplierByCnpj(requestDTO.cnpj()).isPresent()){
            throw new CnpjAlreadyExistsException(requestDTO.cnpj());
        }
        String name = requestDTO.name().trim().toLowerCase();
        if(getSupplierByName(name).isPresent()){
            throw new SupplierNameAlreadyExistsException(name);
        }
        SupplierEntity entity = supplierMapper.toEntity(requestDTO);
        entity.setName(name);

        SupplierEntity savedEntity = supplierRepository.save(entity);
        return supplierMapper.toDomain(savedEntity);
    }

    @Override
    public void deleteSupplierById(Long id) {
        if(getSupplierById(id).isEmpty()){
            throw new SupplierNotFoundException(id.toString());
        }
        supplierRepository.deleteById(id);
    }

    @Override
    public Supplier patchSupplierById(Long id, SupplierPatchDTO patchDTO) {
        Supplier supplierToUpdate = getSupplierById(id)
                .orElseThrow(() -> new SupplierNotFoundException(id.toString()));

        SupplierEntity supplierEntity = supplierMapper.toEntity(supplierToUpdate);

        if (patchDTO.name().isPresent()) {
            String newName = patchDTO.name().get();
            if (!newName.equals(supplierEntity.getName())) {
                if (getSupplierByName(newName).isPresent()) {
                    throw new SupplierNameAlreadyExistsException(newName);
                }
                supplierEntity.setName(newName);
            } else {
                throw new SupplierNameAlreadyExistsException(newName);
            }
        }

        if (patchDTO.cnpj().isPresent()) {
            String newCnpj = patchDTO.cnpj().get();
            if (!newCnpj.equals(supplierEntity.getCnpj())) {
                if (getSupplierByCnpj(newCnpj).isPresent()) {
                    throw new CnpjAlreadyExistsException(newCnpj);
                }
                supplierEntity.setCnpj(newCnpj);
            } else {
                throw new CnpjAlreadyExistsException(newCnpj);
            }
        }

        if (patchDTO.sales_phone().isPresent()) {
            supplierEntity.setSales_phone(patchDTO.sales_phone().get());
        }

        if (patchDTO.contact_phone().isPresent()) {
            supplierEntity.setContact_phone(patchDTO.contact_phone().get());
        }

        SupplierEntity savedEntity = supplierRepository.save(supplierEntity);
        return supplierMapper.toDomain(savedEntity);
    }
}
