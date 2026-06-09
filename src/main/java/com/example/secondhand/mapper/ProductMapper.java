package com.example.secondhand.mapper;

import com.example.secondhand.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> findAll();
    Product findById(Long productId);
    List<Product> findByUserId(Long userId);

    List<Product> searchProducts(@Param("keyword") String keyword,
                                 @Param("categoryId") Long categoryId,
                                 @Param("minPrice") Double minPrice,
                                 @Param("maxPrice") Double maxPrice);

    int insertProduct(Product product);
    int updateProduct(Product product);
    int deleteById(Long productId);
    int updateStatus(@Param("productId") Long productId, @Param("status") String status);
}