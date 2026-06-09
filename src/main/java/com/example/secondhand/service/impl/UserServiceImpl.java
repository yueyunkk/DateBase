package com.example.secondhand.service.impl;

import com.example.secondhand.entity.User;
import com.example.secondhand.mapper.UserMapper;
import com.example.secondhand.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            return null;
        }
        if (!user.getPassword().equals(password)) {
            return null;
        }
        if (user.getStatus() != null && user.getStatus() == 0) {
            return null;
        }
        return user;
    }

    @Override
    public void register(User user) {
        User dbUser = userMapper.findByUsername(user.getUsername());
        if (dbUser != null) {
            throw new RuntimeException("用户名已存在");
        }
        user.setRole("user");
        user.setStatus(1);
        user.setRegisterTime(LocalDateTime.now());
        userMapper.insertUser(user);
    }

    @Override
    public User findById(Long userId) {
        return userMapper.findById(userId);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}