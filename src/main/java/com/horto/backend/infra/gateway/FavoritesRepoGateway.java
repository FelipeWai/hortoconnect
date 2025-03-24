package com.horto.backend.infra.gateway;

import com.horto.backend.core.entities.Favorites;
import com.horto.backend.core.entities.Product;
import com.horto.backend.core.entities.User;
import com.horto.backend.core.exceptions.favorites.FavoriteNotFoundException;
import com.horto.backend.core.gateway.FavoritesGateway;
import com.horto.backend.core.usecases.product.get.GetProductByIdCase;
import com.horto.backend.core.usecases.user.get.GetUserByIdCase;
import com.horto.backend.infra.dto.favorites.request.FavoritesRequestDTO;
import com.horto.backend.infra.mapper.FavoritesMapper;
import com.horto.backend.infra.mapper.ProductMapper;
import com.horto.backend.infra.mapper.UserMapper;
import com.horto.backend.infra.persistence.entities.FavoritesEntity;
import com.horto.backend.infra.persistence.repositories.FavoritesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FavoritesRepoGateway implements FavoritesGateway {

    private final GetUserByIdCase getUserByIdCase;
    private final GetProductByIdCase getProductByIdCase;

    private final FavoritesRepository favoritesRepository;

    private final FavoritesMapper favoritesMapper;
    private final ProductMapper productMapper;
    private final UserMapper userMapper;

    @Override
    public List<Favorites> getAllFavorites() {
        List<FavoritesEntity> favoritesEntities = favoritesRepository.findAll();
        return favoritesEntities.stream()
                .map(favoritesMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Favorites> getFavoritesByUserId(Long userId) {

        getUserByIdCase.execute(userId);

        List<FavoritesEntity> favoritesEntityList = favoritesRepository.findAllByUser_Id(userId);
        return favoritesEntityList.stream()
                .map(favoritesMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Favorites> getFavoriteById(Long id) {
        Optional<FavoritesEntity> favoritesEntityOptional = favoritesRepository.findById(id);
        return favoritesEntityOptional.map(favoritesMapper::toDomain);
    }

    @Override
    public Favorites createFavorite(FavoritesRequestDTO favoritesRequestDTO) {
        User user = getUserByIdCase.execute(favoritesRequestDTO.user_id());
        Product product = getProductByIdCase.execute(favoritesRequestDTO.product_id());

        FavoritesEntity favoritesEntity = favoritesMapper.toEntity(favoritesRequestDTO);
        favoritesEntity.setUser(userMapper.toEntity(user));
        favoritesEntity.setProduct(productMapper.toEntity(product));

        FavoritesEntity createdEntity = favoritesRepository.save(favoritesEntity);

        return favoritesMapper.toDomain(createdEntity);
    }

    @Transactional
    @Override
    public void deleteFavoriteById(Long id) {
        if(getFavoriteById(id).isEmpty()){
            throw new FavoriteNotFoundException(id.toString());
        }
        favoritesRepository.deleteById(id);
    }
}
