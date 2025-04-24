package com.horto.backend.infra.mapper;

import com.horto.backend.core.entities.Subscription;
import com.horto.backend.infra.persistence.entities.SubscriptionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    Subscription toDomain(SubscriptionEntity entity);

    SubscriptionEntity toEntity(Subscription domain);

}
