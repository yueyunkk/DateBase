package com.example.secondhand.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Message {
    private Long messageId;
    private Long userId;
    private Long productId;
    private String content;
    private LocalDateTime messageTime;

    private String username;
}