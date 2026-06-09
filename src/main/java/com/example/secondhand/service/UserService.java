package com.example.secondhand.service;

import com.example.secondhand.entity.User;

import java.util.List;

public interface UserService {
    User login(String username, String password);

    void register(User user);

    User findById(Long userId);

    void updateUser(User user);

    List<User> findAll();
}