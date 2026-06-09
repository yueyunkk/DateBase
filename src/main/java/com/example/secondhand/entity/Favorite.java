package com.example.secondhand.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Favorite {
    private Long favoriteId;
    private Long userId;
    private Long productId;
    private LocalDateTime favoriteTime;

    private String productName;
    private String imageUrl;
    private String sellerName;
    private java.math.BigDecimal price;
}