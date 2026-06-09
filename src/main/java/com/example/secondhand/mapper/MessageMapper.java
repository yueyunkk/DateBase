package com.example.secondhand.mapper;

import com.example.secondhand.entity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MessageMapper {
    int insertMessage(Message message);
    List<Message> findByProductId(Long productId);
}