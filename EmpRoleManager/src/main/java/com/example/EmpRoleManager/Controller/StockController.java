package com.example.EmpRoleManager.Controller;

import com.example.EmpRoleManager.Entity.Stock;
import com.example.EmpRoleManager.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/addstock")
    public ResponseEntity<Map<String , Object>> addStock(@RequestBody Stock stock){
        try{
            Map<String ,Object> res = stockService.addStock(stock);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(Map.of("message", "Failed to added", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllStock")
    public ResponseEntity<Map<String,Object>>getAllStock(){
        try{
            Map<String,Object>res= stockService.getAllStock();
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Map.of("message","failed to get","error",e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getcategorydata")
    public ResponseEntity<Map<String,Object>> getStockCategory(@RequestParam String category){
        try{
            Map<String,Object> res = stockService.getStockCategory(category);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Map.of("message","failed to get","error",e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
