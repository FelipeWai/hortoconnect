package com.horto.backend.core.usecases.favorites.post;

import com.horto.backend.core.entities.Favorites;
import com.horto.backend.core.gateway.FavoritesGateway;
import com.horto.backend.infra.dto.favorites.request.FavoritesRequestDTO;

public class CreateFavoriteCaseImpl implements CreateFavoriteCase {
    private final FavoritesGateway favoritesGateway;

    public CreateFavoriteCaseImpl(FavoritesGateway favoritesGateway) {
        this.favoritesGateway = favoritesGateway;
    }

    @Override
    public Favorites execute(FavoritesRequestDTO favoritesRequestDTO) {
        return favoritesGateway.createFavorite(favoritesRequestDTO);
    }
}
