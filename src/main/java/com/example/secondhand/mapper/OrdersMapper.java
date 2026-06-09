package com.example.secondhand.mapper;

import com.example.secondhand.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrdersMapper {
    int insertOrder(Orders orders);
    Orders findById(Long orderId);
    List<Orders> findByBuyerId(Long buyerId);
    List<Orders> findBySellerId(Long sellerId);
    List<Orders> findAll();
    int updateOrderStatus(@Param("orderId") Long orderId,
                          @Param("orderStatus") String orderStatus);
}