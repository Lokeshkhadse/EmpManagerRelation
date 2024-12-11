package com.example.EmpRoleManager.ExternalService;

import com.example.EmpRoleManager.Entity.Stock;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "STOCK",url="http://localhost:2001/stock")
public interface StockService {


      @PostMapping("/addstock")
      Map<String, Object>addStock(@RequestBody Stock stock);


      @GetMapping("/getAllStock")
      Map<String, Object>getAllStock();

      @GetMapping("/getcategorydata")
      Map<String, Object>getStockCategory(@RequestParam String category);
}
