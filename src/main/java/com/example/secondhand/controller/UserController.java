package com.example.secondhand.controller;

import com.example.secondhand.entity.User;
import com.example.secondhand.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        User user = userService.findById(loginUser.getUserId());
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/update")
    public String update(User user, HttpSession session) {
        userService.updateUser(user);
        User newUser = userService.findById(user.getUserId());
        session.setAttribute("loginUser", newUser);
        return "redirect:/user/profile";
    }

    @GetMapping("/list")
    public String userList(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (!"admin".equals(loginUser.getRole())) {
            return "redirect:/index";
        }
        model.addAttribute("users", userService.findAll());
        return "admin-user";
    }
}