package com.horto.backend.core.usecases.favorites.get;

import com.horto.backend.core.entities.Favorites;

import java.util.List;

public interface GetAllFavoritesCase {

    List<Favorites> execute();

}
