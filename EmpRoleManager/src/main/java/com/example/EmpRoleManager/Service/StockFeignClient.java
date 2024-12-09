package com.example.EmpRoleManager.Service;

import com.example.EmpRoleManager.Entity.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "stock-service",url = "http://localhost:2000/stock")
public interface StockFeignClient {

    @PostMapping("/addstock")
    ResponseEntity<Map<String, Object>> addStock(@RequestBody Stock stock);

    @GetMapping("/getcategorydata")
    ResponseEntity<Map<String, Object>> getStockByCategory(@RequestParam String category);
}
