package com.horto.backend.core.usecases.favorites.get;

import com.horto.backend.core.entities.Favorites;

import java.util.List;

public interface GetFavoritesByUserIdCase {

    List<Favorites> execute(Long userId);

}
