package com.example.secondhand.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface AnalysisMapper {
    List<Map<String, Object>> countProductByCategory();
    List<Map<String, Object>> avgPriceByCategory();
    List<Map<String, Object>> monthlyDealCount();
    Map<String, Object> priceRangeCount();
    List<Map<String, Object>> userPublishRank();
}