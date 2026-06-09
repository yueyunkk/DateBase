package com.example.secondhand.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/errorPage")
    public String errorPage() {
        return "error";
    }
}