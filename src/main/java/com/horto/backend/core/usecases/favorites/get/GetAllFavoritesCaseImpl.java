package com.horto.backend.core.usecases.favorites.get;

import com.horto.backend.core.entities.Favorites;
import com.horto.backend.core.gateway.FavoritesGateway;

import java.util.List;

public class GetAllFavoritesCaseImpl implements GetAllFavoritesCase {

    private final FavoritesGateway favoritesGateway;

    public GetAllFavoritesCaseImpl(FavoritesGateway favoritesGateway) {
        this.favoritesGateway = favoritesGateway;
    }

    @Override
    public List<Favorites> execute() {
        return favoritesGateway.getAllFavorites();
    }
}
