package com.example.Incentive.Controller;

import com.example.Incentive.Dto.IncentiveDto;
import com.example.Incentive.Entity.Stock;
import com.example.Incentive.ExternalService.StockService;
import com.example.Incentive.Service.IncentiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/incentive")
public class IncentiveController {

    @Autowired
    private IncentiveService incentiveService;

    @Autowired
    private StockService stockService;

    @PostMapping("/add")
    public ResponseEntity<Map<String,Object>> addIncentive(@RequestBody IncentiveDto incentiveDto){
        try{
            Map<String,Object>res = incentiveService.addIncentive(incentiveDto);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(Map.of("message","failed to added","error",e.getMessage()),HttpStatus.BAD_REQUEST);

        }
    }


//    @GetMapping("/getstockId/{id}")
//    public ResponseEntity<Map<String,Object>> getStockByid(@PathVariable("id") int id){
//        try{
//            Map<String,Object> res = stockService.getStockByid(id);
//            return new ResponseEntity<>(res,HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(Map.of("message","failed to get","error",e.getMessage()),HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @PutMapping("/updatestock/{id}")
//    public ResponseEntity<Map<String , Object>> updateStock(@PathVariable("id") int id, @RequestBody Stock stock){
//        try{
//            Map<String ,Object> res = stockService.updateStock(id,stock);
//            return new ResponseEntity<>(res, HttpStatus.CREATED);
//        }catch (Exception e){
//            return new ResponseEntity<>(Map.of("message", "Failed to added", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//    }
}
