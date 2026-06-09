package com.example.secondhand.mapper;

import com.example.secondhand.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> findAll();
    Category findById(Long categoryId);
    int insertCategory(Category category);
    int updateCategory(Category category);
    int deleteById(Long categoryId);
}