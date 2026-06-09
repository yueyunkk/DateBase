package com.example.secondhand.service;

import java.util.List;
import java.util.Map;

public interface AnalysisService {
    List<Map<String, Object>> countProductByCategory();

    List<Map<String, Object>> avgPriceByCategory();

    List<Map<String, Object>> monthlyDealCount();

    Map<String, Object> priceRangeCount();

    List<Map<String, Object>> userPublishRank();
}