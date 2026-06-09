package com.example.secondhand.service;

import com.example.secondhand.entity.Message;

import java.util.List;

public interface MessageService {
    void addMessage(Message message);

    List<Message> findByProductId(Long productId);
}