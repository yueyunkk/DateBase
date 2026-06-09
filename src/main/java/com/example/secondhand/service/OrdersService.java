package com.example.secondhand.service;

import com.example.secondhand.entity.Orders;

import java.util.List;

public interface OrdersService {
    void createOrder(Long productId, Long buyerId);

    List<Orders> findByBuyerId(Long buyerId);

    List<Orders> findBySellerId(Long sellerId);

    List<Orders> findAll();

    Orders findById(Long orderId);

    void completeOrder(Long orderId);

    void cancelOrder(Long orderId);
}