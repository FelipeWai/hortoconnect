package com.horto.backend.infra.config.usecases;

import com.horto.backend.core.usecases.favorites.delete.DeleteFavoriteByIdCase;
import com.horto.backend.core.usecases.favorites.delete.DeleteFavoriteByIdCaseImpl;
import com.horto.backend.core.usecases.favorites.get.*;
import com.horto.backend.core.usecases.favorites.post.CreateFavoriteCase;
import com.horto.backend.core.usecases.favorites.post.CreateFavoriteCaseImpl;
import com.horto.backend.infra.gateway.FavoritesRepoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FavoritesUseCasesConfig {

    private final FavoritesRepoGateway favoritesRepoGateway;

    @Bean
    public GetAllFavoritesCase getAllFavoritesCase() {
        return new GetAllFavoritesCaseImpl(favoritesRepoGateway);
    }

    @Bean
    public GetFavoritesByUserIdCase getFavoritesByUserIdCase() {
        return new GetFavoritesByUserIdCaseImpl(favoritesRepoGateway);
    }

    @Bean
    public GetFavoritesByIdCase getFavoritesByIdCase() {
        return new GetFavoritesByIdCaseImpl(favoritesRepoGateway);
    }

    @Bean
    public CreateFavoriteCase createFavoriteCase() {
        return new CreateFavoriteCaseImpl(favoritesRepoGateway);
    }

    @Bean
    public DeleteFavoriteByIdCase deleteFavoriteByIdCase() {
        return new DeleteFavoriteByIdCaseImpl(favoritesRepoGateway);
    }

}
