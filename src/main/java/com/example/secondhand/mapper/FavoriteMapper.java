package com.example.secondhand.mapper;

import com.example.secondhand.entity.Favorite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FavoriteMapper {
    int insertFavorite(Favorite favorite);
    int deleteFavorite(@Param("userId") Long userId, @Param("productId") Long productId);
    Favorite findOne(@Param("userId") Long userId, @Param("productId") Long productId);
    List<Favorite> findByUserId(Long userId);
}