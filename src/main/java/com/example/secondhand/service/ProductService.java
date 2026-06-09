package com.example.secondhand.service;

import com.example.secondhand.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(Long productId);

    List<Product> findByUserId(Long userId);

    List<Product> searchProducts(String keyword, Long categoryId, Double minPrice, Double maxPrice);

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteById(Long productId);

    void updateStatus(Long productId, String status);
}