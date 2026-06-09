package com.example.secondhand.controller;

import com.example.secondhand.entity.Message;
import com.example.secondhand.entity.User;
import com.example.secondhand.service.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/add")
    public String addMessage(Message message, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        message.setUserId(loginUser.getUserId());
        messageService.addMessage(message);
        return "redirect:/product/detail/" + message.getProductId();
    }
}