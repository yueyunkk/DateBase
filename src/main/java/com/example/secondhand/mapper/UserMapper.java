package com.example.secondhand.mapper;

import com.example.secondhand.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findByUsername(String username);
    User findById(Long userId);
    int insertUser(User user);
    int updateUser(User user);
    List<User> findAll();
}