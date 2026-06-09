package com.example.secondhand.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Orders {
    private Long orderId;
    private Long productId;
    private Long buyerId;
    private Long sellerId;
    private BigDecimal orderPrice;
    private String orderStatus;
    private LocalDateTime orderTime;
    private LocalDateTime finishTime;
    private String remark;

    private String productName;
    private String buyerName;
    private String sellerName;
}