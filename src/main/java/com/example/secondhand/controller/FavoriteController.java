package com.example.secondhand.controller;

import com.example.secondhand.entity.User;
import com.example.secondhand.service.FavoriteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/add/{productId}")
    public String addFavorite(@PathVariable Long productId, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        favoriteService.addFavorite(loginUser.getUserId(), productId);
        return "redirect:/product/detail/" + productId;
    }

    @GetMapping("/remove/{productId}")
    public String removeFavorite(@PathVariable Long productId, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        favoriteService.removeFavorite(loginUser.getUserId(), productId);
        return "redirect:/product/detail/" + productId;
    }

    @GetMapping("/my")
    public String myFavorite(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        model.addAttribute("favorites", favoriteService.findByUserId(loginUser.getUserId()));
        return "my-favorite";
    }
}