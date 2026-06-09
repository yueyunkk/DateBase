package com.example.secondhand.service.impl;

import com.example.secondhand.entity.Category;
import com.example.secondhand.mapper.CategoryMapper;
import com.example.secondhand.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    @Override
    public Category findById(Long categoryId) {
        return categoryMapper.findById(categoryId);
    }

    @Override
    public void addCategory(Category category) {
        if (category.getStatus() == null) {
            category.setStatus(1);
        }
        if (category.getSortNo() == null) {
            category.setSortNo(0);
        }
        categoryMapper.insertCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.updateCategory(category);
    }

    @Override
    public void deleteById(Long categoryId) {
        categoryMapper.deleteById(categoryId);
    }
}