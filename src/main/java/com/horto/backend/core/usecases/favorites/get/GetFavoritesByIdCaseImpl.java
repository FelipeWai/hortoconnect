package com.horto.backend.core.usecases.favorites.get;

import com.horto.backend.core.entities.Favorites;
import com.horto.backend.core.exceptions.favorites.FavoriteNotFoundException;
import com.horto.backend.core.gateway.FavoritesGateway;

public class GetFavoritesByIdCaseImpl implements GetFavoritesByIdCase {
    private final FavoritesGateway favoritesGateway;

    public GetFavoritesByIdCaseImpl(FavoritesGateway favoritesGateway) {
        this.favoritesGateway = favoritesGateway;
    }

    @Override
    public Favorites execute(Long id) {
        return favoritesGateway.getFavoriteById(id)
                .orElseThrow(() -> new FavoriteNotFoundException(id.toString()));
    }
}
