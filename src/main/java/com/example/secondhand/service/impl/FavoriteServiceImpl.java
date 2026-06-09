package com.example.secondhand.service.impl;

import com.example.secondhand.entity.Favorite;
import com.example.secondhand.mapper.FavoriteMapper;
import com.example.secondhand.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Override
    public void addFavorite(Long userId, Long productId) {
        Favorite favorite = favoriteMapper.findOne(userId, productId);
        if (favorite != null) {
            return;
        }
        Favorite f = new Favorite();
        f.setUserId(userId);
        f.setProductId(productId);
        f.setFavoriteTime(LocalDateTime.now());
        favoriteMapper.insertFavorite(f);
    }

    @Override
    public void removeFavorite(Long userId, Long productId) {
        favoriteMapper.deleteFavorite(userId, productId);
    }

    @Override
    public boolean isFavorite(Long userId, Long productId) {
        return favoriteMapper.findOne(userId, productId) != null;
    }

    @Override
    public List<Favorite> findByUserId(Long userId) {
        return favoriteMapper.findByUserId(userId);
    }
}