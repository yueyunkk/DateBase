package com.example.secondhand.service.impl;

import com.example.secondhand.entity.Orders;
import com.example.secondhand.entity.Product;
import com.example.secondhand.mapper.OrdersMapper;
import com.example.secondhand.mapper.ProductMapper;
import com.example.secondhand.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public void createOrder(Long productId, Long buyerId) {
        Product product = productMapper.findById(productId);
        if (product == null) {
            throw new RuntimeException("商品不存在");
        }

        if ("SOLD".equals(product.getStatus())) {
            throw new RuntimeException("商品已售出");
        }

        if (buyerId.equals(product.getUserId())) {
            throw new RuntimeException("不能购买自己的商品");
        }

        Orders orders = new Orders();
        orders.setProductId(productId);
        orders.setBuyerId(buyerId);
        orders.setSellerId(product.getUserId());
        orders.setOrderPrice(product.getPrice());
        orders.setOrderStatus("PENDING");
        orders.setOrderTime(LocalDateTime.now());
        orders.setRemark("正常下单");
        ordersMapper.insertOrder(orders);

        productMapper.updateStatus(productId, "OFF_SALE");
    }

    @Override
    public List<Orders> findByBuyerId(Long buyerId) {
        return ordersMapper.findByBuyerId(buyerId);
    }

    @Override
    public List<Orders> findBySellerId(Long sellerId) {
        return ordersMapper.findBySellerId(sellerId);
    }

    @Override
    public List<Orders> findAll() {
        return ordersMapper.findAll();
    }

    @Override
    public Orders findById(Long orderId) {
        return ordersMapper.findById(orderId);
    }

    @Override
    public void completeOrder(Long orderId) {
        Orders orders = ordersMapper.findById(orderId);
        if (orders == null) {
            throw new RuntimeException("订单不存在");
        }
        ordersMapper.updateOrderStatus(orderId, "COMPLETED");
        productMapper.updateStatus(orders.getProductId(), "SOLD");
    }

    @Override
    public void cancelOrder(Long orderId) {
        Orders orders = ordersMapper.findById(orderId);
        if (orders == null) {
            throw new RuntimeException("订单不存在");
        }
        ordersMapper.updateOrderStatus(orderId, "CANCELLED");
        productMapper.updateStatus(orders.getProductId(), "ON_SALE");
    }
}