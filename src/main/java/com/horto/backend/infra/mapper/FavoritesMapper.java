package com.horto.backend.infra.mapper;

import com.horto.backend.core.entities.Favorites;
import com.horto.backend.core.entities.ProductPicture;
import com.horto.backend.infra.dto.favorites.request.FavoritesRequestDTO;
import com.horto.backend.infra.dto.favorites.response.FavoritesResponseDTO;
import com.horto.backend.infra.persistence.entities.FavoritesEntity;
import com.horto.backend.infra.persistence.entities.ProductPictureEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FavoritesMapper {

    // Mapeamento de fotos para evitar recursão infinta
    @Mapping(target = "product", ignore = true)
    ProductPicture toDomain(ProductPictureEntity entity);

    Favorites toDomain(FavoritesEntity entity);

    FavoritesResponseDTO toResponseDTO(Favorites favorites);

    FavoritesEntity toEntity(Favorites favorites);
    FavoritesEntity toEntity(FavoritesRequestDTO favoritesRequestDTO);

}
