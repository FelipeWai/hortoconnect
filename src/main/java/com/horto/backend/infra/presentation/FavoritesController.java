package com.horto.backend.infra.presentation;

import com.horto.backend.core.entities.Favorites;
import com.horto.backend.core.usecases.favorites.delete.DeleteFavoriteByIdCase;
import com.horto.backend.core.usecases.favorites.get.GetAllFavoritesCase;
import com.horto.backend.core.usecases.favorites.get.GetFavoritesByUserIdCase;
import com.horto.backend.core.usecases.favorites.post.CreateFavoriteCase;
import com.horto.backend.infra.dto.favorites.request.FavoritesRequestDTO;
import com.horto.backend.infra.dto.favorites.response.FavoritesResponseDTO;
import com.horto.backend.infra.mapper.FavoritesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoritesController {

    private final DeleteFavoriteByIdCase deleteFavoriteByIdCase;

    private final CreateFavoriteCase createFavoriteCase;

    private final GetAllFavoritesCase getAllFavoritesCase;
    private final GetFavoritesByUserIdCase getFavoritesByUserIdCase;

    private final FavoritesMapper favoritesMapper;


    @GetMapping
    public ResponseEntity<List<FavoritesResponseDTO>> getAllFavorites() {
        List<Favorites> favorites = getAllFavoritesCase.execute();
        return ResponseEntity.ok(favorites.stream()
                .map(favoritesMapper::toResponseDTO)
                .toList()
        );
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<FavoritesResponseDTO>> getFavoritesByUserId(@PathVariable Long id) {
        List<Favorites> favorites = getFavoritesByUserIdCase.execute(id);
        return ResponseEntity.ok(favorites.stream()
                .map(favoritesMapper::toResponseDTO)
                .toList());
    }

    @PostMapping
    public ResponseEntity<FavoritesResponseDTO> createFavorite(@RequestBody FavoritesRequestDTO favoritesRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(favoritesMapper.toResponseDTO(createFavoriteCase.execute(favoritesRequestDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFavorite(@PathVariable Long id) {
        deleteFavoriteByIdCase.execute(id);
        return ResponseEntity.noContent().build();
    }

}
