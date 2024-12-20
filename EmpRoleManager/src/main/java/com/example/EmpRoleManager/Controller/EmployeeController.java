package com.example.EmpRoleManager.Controller;

import com.example.EmpRoleManager.Entity.Employee;
import com.example.EmpRoleManager.Entity.Stock;
import com.example.EmpRoleManager.ExternalService.StockService;
import com.example.EmpRoleManager.Service.EmployeeService;
import com.example.EmpRoleManager.WebClientService.StockWebClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/emp")
public class EmployeeController
{

   @Autowired
    private EmployeeService employeeService;
   
   @Autowired
   private StockService stockService;

   @Autowired
   private StockWebClientService stockWebClientService;

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee addedEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
    }

    //@RequestMapping(value = "emp/add/excel",method = RequestMethod.POST)
    @PostMapping("/add/excel")
    public ResponseEntity<Map<String,Object>> uploadEmployeesExcel(@RequestParam("file")MultipartFile file){
        try {
            Map<String, Object> result = employeeService.uploadEmployeesExcel(file);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Failed to process file", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/{emp_id}/updateEmail")
    public ResponseEntity<Map<String, Object>> updateEmail(@PathVariable("emp_id") int empId, @RequestBody Map<String, String> emailRequest) {
        try {
            String email = emailRequest.get("mail");
            if (email == null || email.isEmpty()) {
                return new ResponseEntity<>(Map.of("message", "Email cannot be empty"), HttpStatus.BAD_REQUEST);
            }

            Map<String, Object> response = employeeService.updateEmail(empId, email);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Failed to update email", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    //feignclient

//    @PostMapping("/addstock")
//    public ResponseEntity<Map<String, Object>> addStock(@RequestBody Stock stock) {
//        try {
//            Map<String, Object> response = stockService.addStock(stock);
//            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(Map.of("message", "Failed to add stock", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    @GetMapping("/getcategorydata")
//    public ResponseEntity<Map<String, Object>> getStockByCategory(@RequestParam String category) {
//        try {
//            Map<String, Object> response = stockService.getStockCategory(category);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(Map.of("message", "Failed to fetch stock by category", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//    }


    //WEBCLIENT

    @PostMapping("/addstock")
    public ResponseEntity<Map<String, Object>> addStockWebclient(@RequestBody Stock stock) {
        try {
            Map<String, Object> response = stockWebClientService.addStock(stock).block(); // Blocking for simplicity
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Failed to add stock", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // Get Stock by Category
    @GetMapping("/getcategorydata")
    public ResponseEntity<Map<String, Object>> getStockByCategoryWebclient(@RequestParam String category) {
        try {
            Map<String, Object> response = stockWebClientService.getStockByCategory(category).block(); // Blocking for simplicity
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("message", "Failed to fetch stock by category", "error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }






}
