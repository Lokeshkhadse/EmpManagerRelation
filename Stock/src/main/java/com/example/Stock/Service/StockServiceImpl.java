package com.example.Stock.Service;

import com.example.Stock.Dao.StockDao;
import com.example.Stock.Entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockDao stockDao;

    @Override
    public Map<String, Object> addStock(Stock stock) {
        Stock savedStock = stockDao.addStock(stock);
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.CREATED.value());
        response.put("success", "Data added successfully");
        response.put("data", savedStock);
        return response;
    }

    @Override
    public Map<String, Object> getAllStock() {
        List<Stock> getrecords = stockDao.getAllStock();
        return Map.of(
                "status", HttpStatus.OK.value(),
                "success", "Fetched all stocks successfully",
                "data", getrecords
        );
    }

    @Override
    public Map<String, Object> getStockCategory(String category) {
        List<Stock> fetdetails = stockDao.getStockCategory(category);
        return Map.of(
                "Status",HttpStatus.OK.value(),
                "success","fetched successfully",
                "data",fetdetails
        );
    }

    @Override
    public Map<String, Object> getStockByid(int id) {
        Stock stock = stockDao.getStockByid(id);
        return Map.of(
                "Status",HttpStatus.OK.value(),
                "success","fetched successfully",
                "data",stock
        );
    }

    @Override
    public Map<String, Object> updateStock(int id,int quantity) {
        int stock1 = stockDao.updateStock(id,quantity);
        return Map.of(
                "Status",HttpStatus.OK.value(),
                "success","fetched successfully",
                "data",stock1);
    }
}

