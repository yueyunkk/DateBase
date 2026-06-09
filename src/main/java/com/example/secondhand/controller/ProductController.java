package com.example.secondhand.controller;

import com.example.secondhand.entity.Product;
import com.example.secondhand.entity.User;
import com.example.secondhand.service.CategoryService;
import com.example.secondhand.service.FavoriteService;
import com.example.secondhand.service.MessageService;
import com.example.secondhand.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        return "product-list";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String keyword,
                         @RequestParam(required = false) Long categoryId,
                         @RequestParam(required = false) Double minPrice,
                         @RequestParam(required = false) Double maxPrice,
                         Model model) {
        model.addAttribute("products",
                productService.searchProducts(keyword, categoryId, minPrice, maxPrice));
        model.addAttribute("categories", categoryService.findAll());
        return "product-list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, HttpSession session, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("messages", messageService.findByProductId(id));

        User loginUser = (User) session.getAttribute("loginUser");
        if (loginUser != null) {
            boolean favorite = favoriteService.isFavorite(loginUser.getUserId(), id);
            model.addAttribute("isFavorite", favorite);
        } else {
            model.addAttribute("isFavorite", false);
        }
        return "product-detail";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "product-add";
    }

    @PostMapping("/add")
    public String add(Product product, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        product.setUserId(loginUser.getUserId());
        product.setStatus("ON_SALE");
        product.setPublishTime(LocalDateTime.now());
        productService.addProduct(product);
        return "redirect:/product/my";
    }

    @GetMapping("/my")
    public String myProduct(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        model.addAttribute("products", productService.findByUserId(loginUser.getUserId()));
        return "my-product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        Product product = productService.findById(id);
        if (product != null && loginUser.getUserId().equals(product.getUserId())) {
            productService.deleteById(id);
        }
        return "redirect:/product/my";
    }

    @GetMapping("/off/{id}")
    public String offSale(@PathVariable("id") Long id, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        Product product = productService.findById(id);
        if (product != null && loginUser.getUserId().equals(product.getUserId())) {
            productService.updateStatus(id, "OFF_SALE");
        }
        return "redirect:/product/my";
    }

    @GetMapping("/on/{id}")
    public String onSale(@PathVariable("id") Long id, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        Product product = productService.findById(id);
        if (product != null && loginUser.getUserId().equals(product.getUserId())) {
            productService.updateStatus(id, "ON_SALE");
        }
        return "redirect:/product/my";
    }
}