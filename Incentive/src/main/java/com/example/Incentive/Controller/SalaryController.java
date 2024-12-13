package com.example.Incentive.Controller;

import com.example.Incentive.Entity.Salary;
import com.example.Incentive.Service.SalaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @Operation(summary = "Update Salary",
            description = "Updates the salary and total amount for a given employee ID.")
    @ApiResponse(responseCode = "200", description = "Successfully updated salary")
    @ApiResponse(responseCode = "400", description = "Invalid input or failure")
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
