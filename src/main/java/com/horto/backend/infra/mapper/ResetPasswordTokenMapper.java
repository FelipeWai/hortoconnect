package com.horto.backend.infra.mapper;

import com.horto.backend.core.entities.ResetPasswordToken;
import com.horto.backend.infra.persistence.entities.ResetPasswordTokenEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResetPasswordTokenMapper {

    ResetPasswordToken toDomain(ResetPasswordTokenEntity entity);

    ResetPasswordTokenEntity toEntity(ResetPasswordToken domain);

}
