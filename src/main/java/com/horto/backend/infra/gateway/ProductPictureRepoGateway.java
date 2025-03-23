package com.horto.backend.infra.gateway;

import com.horto.backend.core.entities.ProductPicture;
import com.horto.backend.core.gateway.ProductPictureGateway;
import com.horto.backend.infra.mapper.ProductPictureMapper;
import com.horto.backend.infra.persistence.entities.ProductPictureEntity;
import com.horto.backend.infra.persistence.repositories.ProductPictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductPictureRepoGateway implements ProductPictureGateway {

    private final ProductPictureRepository repository;

    private final ProductPictureMapper productPictureMapper;

    @Override
    public List<ProductPicture> createProductPictures(List<ProductPicture> productPictures) {
        List<ProductPictureEntity> entities = productPictures.stream()
                .map(productPictureMapper::toEntity)
                .toList();

        List<ProductPictureEntity> savedEntities = repository.saveAll(entities);

        return savedEntities.stream()
                .map(productPictureMapper::toDomain)
                .toList();
    }
}
