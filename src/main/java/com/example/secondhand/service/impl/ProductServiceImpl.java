package com.example.secondhand.service.impl;

import com.example.secondhand.entity.Product;
import com.example.secondhand.mapper.ProductMapper;
import com.example.secondhand.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findAll() {
        return productMapper.findAll();
    }

    @Override
    public Product findById(Long productId) {
        return productMapper.findById(productId);
    }

    @Override
    public List<Product> findByUserId(Long userId) {
        return productMapper.findByUserId(userId);
    }

    @Override
    public List<Product> searchProducts(String keyword, Long categoryId, Double minPrice, Double maxPrice) {
        return productMapper.searchProducts(keyword, categoryId, minPrice, maxPrice);
    }

    @Override
    public void addProduct(Product product) {
        productMapper.insertProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }

    @Override
    public void deleteById(Long productId) {
        productMapper.deleteById(productId);
    }

    @Override
    public void updateStatus(Long productId, String status) {
        productMapper.updateStatus(productId, status);
    }
}