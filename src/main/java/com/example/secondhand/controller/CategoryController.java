package com.example.secondhand.controller;

import com.example.secondhand.entity.Category;
import com.example.secondhand.entity.User;
import com.example.secondhand.service.CategoryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String list(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (!"admin".equals(loginUser.getRole())) {
            return "redirect:/index";
        }
        model.addAttribute("categories", categoryService.findAll());
        return "category-list";
    }

    @PostMapping("/add")
    public String add(Category category, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (!"admin".equals(loginUser.getRole())) {
            return "redirect:/index";
        }
        categoryService.addCategory(category);
        return "redirect:/category/list";
    }

    @PostMapping("/update")
    public String update(Category category, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (!"admin".equals(loginUser.getRole())) {
            return "redirect:/index";
        }
        categoryService.updateCategory(category);
        return "redirect:/category/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (!"admin".equals(loginUser.getRole())) {
            return "redirect:/index";
        }
        categoryService.deleteById(id);
        return "redirect:/category/list";
    }
}