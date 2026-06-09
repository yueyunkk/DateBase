package com.example.secondhand.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Product {
    private Long productId;
    private Long userId;
    private Long categoryId;
    private String productName;
    private String productDesc;
    private BigDecimal price;
    private BigDecimal originalPrice;
    private String qualityLevel;
    private String imageUrl;
    private String status;
    private LocalDateTime publishTime;

    private String categoryName;
    private String sellerName;
}