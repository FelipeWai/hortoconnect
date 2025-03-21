package com.horto.backend.infra.mapper;

import com.horto.backend.core.entities.User;
import com.horto.backend.infra.dto.user.request.RegisterRequestDTO;
import com.horto.backend.infra.dto.user.response.UserResponseDTO;
import com.horto.backend.infra.persistence.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity(RegisterRequestDTO registerRequestDTO);

    User toDomain(UserEntity entity);

    UserResponseDTO toResponseDTO(User user);

}
