package com.example.secondhand.controller;

import com.example.secondhand.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/analysis")
public class AnalysisController {

    @Autowired
    private AnalysisService analysisService;

    @GetMapping
    public String analysisPage() {
        return "analysis";
    }

    @ResponseBody
    @GetMapping("/category-count")
    public List<Map<String, Object>> categoryCount() {
        return analysisService.countProductByCategory();
    }

    @ResponseBody
    @GetMapping("/avg-price")
    public List<Map<String, Object>> avgPrice() {
        return analysisService.avgPriceByCategory();
    }

    @ResponseBody
    @GetMapping("/monthly-deal")
    public List<Map<String, Object>> monthlyDeal() {
        return analysisService.monthlyDealCount();
    }

    @ResponseBody
    @GetMapping("/price-range")
    public Map<String, Object> priceRange() {
        return analysisService.priceRangeCount();
    }

    @ResponseBody
    @GetMapping("/user-rank")
    public List<Map<String, Object>> userRank() {
        return analysisService.userPublishRank();
    }
}