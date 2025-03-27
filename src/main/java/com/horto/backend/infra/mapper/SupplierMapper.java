package com.horto.backend.infra.mapper;

import com.horto.backend.core.entities.Supplier;
import com.horto.backend.infra.dto.supplier.request.SupplierRequestDTO;
import com.horto.backend.infra.dto.supplier.response.SupplierResponseDTO;
import com.horto.backend.infra.persistence.entities.SupplierEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    Supplier toDomain(SupplierEntity entity);

    SupplierEntity toEntity(Supplier domain);
    SupplierEntity toEntity(SupplierRequestDTO requestDTO);

    SupplierResponseDTO toResponseDTO(SupplierEntity entity);
    SupplierResponseDTO toResponseDTO(Supplier domain);

}
