package com.example.secondhand.service.impl;

import com.example.secondhand.entity.Message;
import com.example.secondhand.mapper.MessageMapper;
import com.example.secondhand.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void addMessage(Message message) {
        message.setMessageTime(LocalDateTime.now());
        messageMapper.insertMessage(message);
    }

    @Override
    public List<Message> findByProductId(Long productId) {
        return messageMapper.findByProductId(productId);
    }
}