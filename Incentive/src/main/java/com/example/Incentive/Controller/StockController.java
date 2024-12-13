package com.example.Incentive.Controller;

import com.example.Incentive.Entity.Salary;
import com.example.Incentive.ExternalService.StockService;
import com.example.Incentive.Service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/salary")
public class StockController {

    @Autowired
    private SalaryService salaryService;

    @PutMapping("/update/{id}/{emp_id}")
    public ResponseEntity<Map<String ,Object>> updatesalary(@PathVariable("id") int id , @PathVariable("emp_id") int emp_id , @RequestBody  Salary salary){
        try{
            Map<String,Object> res = salaryService.updatesalary(id,emp_id,salary);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Map.of("message","failed to update","error",e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
