package com.example.secondhand.controller;

import com.example.secondhand.entity.User;
import com.example.secondhand.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(String username, String password, HttpSession session, Model model) {
        try {
            User user = userService.login(username, password);
            if (user == null) {
                model.addAttribute("error", "用户名或密码错误，或账号已被禁用");
                return "login";
            }
            session.setAttribute("loginUser", user);
            return "redirect:/index";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/doRegister")
    public String doRegister(User user, Model model) {
        try {
            userService.register(user);
            model.addAttribute("success", "注册成功，请登录");
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}