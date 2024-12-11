package com.example.Stock.Service;

import com.example.Stock.Entity.Stock;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

public interface StockService {

    Map<String, Object> addStock(Stock stock);

    Map<String, Object> getAllStock();

    Map<String, Object> getStockCategory(String category);

    Map<String, Object> getStockByid(int id);

    Map<String, Object> updateStock(int id,int quantity);
}
