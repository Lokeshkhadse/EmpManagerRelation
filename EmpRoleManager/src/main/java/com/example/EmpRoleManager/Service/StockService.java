package com.example.EmpRoleManager.Service;

import com.example.EmpRoleManager.Entity.Stock;

import java.util.Map;

public interface StockService {
    Map<String, Object> addStock(Stock stock);

    Map<String, Object> getAllStock();

    Map<String, Object> getStockCategory(String category);
}
