package com.horto.backend.core.usecases.favorites.post;

import com.horto.backend.core.entities.Favorites;
import com.horto.backend.infra.dto.favorites.request.FavoritesRequestDTO;

public interface CreateFavoriteCase {

    Favorites execute(FavoritesRequestDTO favoritesRequestDTO);

}
