package com.example.secondhand.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class User {
    private Long userId;
    private String username;
    private String password;
    private String realName;
    private String gender;
    private String phone;
    private String email;
    private String role;
    private Integer status;
    private LocalDateTime registerTime;
}