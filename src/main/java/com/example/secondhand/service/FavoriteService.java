package com.example.secondhand.service;

import com.example.secondhand.entity.Favorite;

import java.util.List;

public interface FavoriteService {
    void addFavorite(Long userId, Long productId);

    void removeFavorite(Long userId, Long productId);

    boolean isFavorite(Long userId, Long productId);

    List<Favorite> findByUserId(Long userId);
}