package com.horto.backend.core.usecases.favorites.get;

import com.horto.backend.core.entities.Favorites;
import com.horto.backend.core.gateway.FavoritesGateway;

import java.util.List;

public class GetFavoritesByUserIdCaseImpl implements GetFavoritesByUserIdCase {

    private final FavoritesGateway favoritesGateway;

    public GetFavoritesByUserIdCaseImpl(FavoritesGateway favoritesGateway) {
        this.favoritesGateway = favoritesGateway;
    }

    @Override
    public List<Favorites> execute(Long userId) {
        return favoritesGateway.getFavoritesByUserId(userId);
    }
}
