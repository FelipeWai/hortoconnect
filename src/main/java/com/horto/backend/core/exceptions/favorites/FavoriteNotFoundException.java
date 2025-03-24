package com.horto.backend.core.exceptions.favorites;

public class FavoriteNotFoundException extends RuntimeException {
    public FavoriteNotFoundException(String message) {
        super("Favorito não encontrado com: " + message);
    }
}
