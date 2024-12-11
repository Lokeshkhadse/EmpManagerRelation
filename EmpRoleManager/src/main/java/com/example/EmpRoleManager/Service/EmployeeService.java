package com.example.EmpRoleManager.Service;

import com.example.EmpRoleManager.Entity.Employee;
import com.example.EmpRoleManager.Entity.Stock;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Map;

public interface EmployeeService {
    Employee addEmployee(Employee employee);

    Map<String, Object> uploadEmployeesExcel(MultipartFile file) throws IOException;


    Map<String, Object> updateEmail(int empId, String email);


    //feign
    public Map<String, Object> addStock(Stock stock);

    public Map<String, Object> getStockByCategory(String category);

    public Mono<Map> getStockByCategorythorughwebClient(String category);

}

