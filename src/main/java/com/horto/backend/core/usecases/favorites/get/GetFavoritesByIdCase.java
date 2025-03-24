package com.horto.backend.core.usecases.favorites.get;

import com.horto.backend.core.entities.Favorites;

public interface GetFavoritesByIdCase {

    Favorites execute(Long id);

}
