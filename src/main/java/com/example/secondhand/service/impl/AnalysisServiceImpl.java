package com.example.secondhand.service.impl;

import com.example.secondhand.mapper.AnalysisMapper;
import com.example.secondhand.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Autowired
    private AnalysisMapper analysisMapper;

    @Override
    public List<Map<String, Object>> countProductByCategory() {
        return analysisMapper.countProductByCategory();
    }

    @Override
    public List<Map<String, Object>> avgPriceByCategory() {
        return analysisMapper.avgPriceByCategory();
    }

    @Override
    public List<Map<String, Object>> monthlyDealCount() {
        return analysisMapper.monthlyDealCount();
    }

    @Override
    public Map<String, Object> priceRangeCount() {
        return analysisMapper.priceRangeCount();
    }

    @Override
    public List<Map<String, Object>> userPublishRank() {
        return analysisMapper.userPublishRank();
    }
}