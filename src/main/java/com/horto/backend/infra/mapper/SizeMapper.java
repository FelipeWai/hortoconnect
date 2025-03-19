package com.horto.backend.infra.mapper;

import com.horto.backend.core.entities.Size;
import com.horto.backend.infra.dto.size.request.SizeRequestDTO;
import com.horto.backend.infra.dto.size.response.SizeResponseDTO;
import com.horto.backend.infra.persistence.entities.SizeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SizeMapper {

    Size toDomain(SizeEntity entity);

    SizeResponseDTO toResponseDTO(Size size);

    SizeEntity toEntity(SizeRequestDTO sizeRequestDTO);
}
