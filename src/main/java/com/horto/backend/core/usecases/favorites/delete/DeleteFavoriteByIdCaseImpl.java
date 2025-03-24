package com.horto.backend.core.usecases.favorites.delete;

import com.horto.backend.core.gateway.FavoritesGateway;

public class DeleteFavoriteByIdCaseImpl implements DeleteFavoriteByIdCase {

    private final FavoritesGateway favoritesGateway;

    public DeleteFavoriteByIdCaseImpl(FavoritesGateway favoritesGateway) {
        this.favoritesGateway = favoritesGateway;
    }


    @Override
    public void execute(Long id) {
        favoritesGateway.deleteFavoriteById(id);
    }
}
