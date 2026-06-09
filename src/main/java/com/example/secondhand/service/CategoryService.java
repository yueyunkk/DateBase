package com.example.secondhand.service;

import com.example.secondhand.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(Long categoryId);

    void addCategory(Category category);

    void updateCategory(Category category);

    void deleteById(Long categoryId);
}