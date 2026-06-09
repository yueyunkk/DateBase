package com.example.secondhand.controller;

import com.example.secondhand.entity.User;
import com.example.secondhand.service.OrdersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/create/{productId}")
    public String createOrder(@PathVariable Long productId, HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        try {
            ordersService.createOrder(productId, loginUser.getUserId());
            return "redirect:/order/my-buy";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/my-buy")
    public String myBuy(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        model.addAttribute("orders", ordersService.findByBuyerId(loginUser.getUserId()));
        model.addAttribute("type", "buy");
        return "my-order";
    }

    @GetMapping("/my-sell")
    public String mySell(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        model.addAttribute("orders", ordersService.findBySellerId(loginUser.getUserId()));
        model.addAttribute("type", "sell");
        return "my-order";
    }

    @GetMapping("/complete/{orderId}")
    public String complete(@PathVariable Long orderId) {
        ordersService.completeOrder(orderId);
        return "redirect:/order/my-sell";
    }

    @GetMapping("/cancel/{orderId}")
    public String cancel(@PathVariable Long orderId) {
        ordersService.cancelOrder(orderId);
        return "redirect:/order/my-buy";
    }

    @GetMapping("/list")
    public String allOrders(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("loginUser");
        if (!"admin".equals(loginUser.getRole())) {
            return "redirect:/index";
        }
        model.addAttribute("orders", ordersService.findAll());
        model.addAttribute("type", "admin");
        return "my-order";
    }
}