package com.horto.backend.core.gateway;

import com.horto.backend.core.entities.Favorites;
import com.horto.backend.infra.dto.favorites.request.FavoritesRequestDTO;

import java.util.List;
import java.util.Optional;

public interface FavoritesGateway {

    List<Favorites> getAllFavorites();

    List<Favorites> getFavoritesByUserId(Long userId);

    Favorites createFavorite(FavoritesRequestDTO favoritesRequestDTO);

    Optional<Favorites> getFavoriteById(Long id);

    void deleteFavoriteById(Long id);

}
