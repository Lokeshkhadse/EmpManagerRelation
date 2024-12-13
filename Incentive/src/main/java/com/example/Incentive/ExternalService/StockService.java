package com.example.Incentive.ExternalService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "STOCK",url = "http://localhost:2001/stock")
public interface StockService {

    @GetMapping("/getstockId/{id}")
    public Map<String, Object> getStockByid(@PathVariable("id") int id);

    @PutMapping("/updatestock/{id}")
    public Map<String, Object> updateStock(@PathVariable("id") int id, @RequestParam int quantity);



}
