package com.example.EmpRoleManager.Service;

import com.example.EmpRoleManager.Dao.EmployeeDao;
import com.example.EmpRoleManager.Dao.RoleDao;
import com.example.EmpRoleManager.Dao.RoleMappingDao;
import com.example.EmpRoleManager.Entity.Employee;
import com.example.EmpRoleManager.Entity.Stock;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements  EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private RoleMappingDao roleMappingDao;

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private EmailService emailService;

    @Autowired
    private StockFeignClient stockFeignClient;

    @Autowired
    private  WebclientService webclientService;

    public Employee addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);

        int role_id = (employee.getEmp_id() == 10) ? 4000 : 1000;
        roleMappingDao.addRoleMapping(employee.getEmp_id(), role_id);

        emailService.sendmail(
                employee.getMail(),
                "Employee Details Updated",
                "Hi " + employee.getName() + ",\n\nYour details have been successfully updated in the system.\n\nThank you!"
        );


        return employee;

    }


    @Override
    public Map<String, Object> uploadEmployeesExcel(MultipartFile file) throws IOException {
        List<Employee> employees = new ArrayList<>();
        int added = 0, updated = 0, skipped = 0;

        // Use Apache POI to read Excel data
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                int empId = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                String phno = getCellValue(row.getCell(2));
                String mail = row.getCell(3).getStringCellValue();


                Employee employee = new Employee(empId, name, phno, mail);

                try {
                    Employee existingEmployee = employeeDao.getEmployee(empId);
                    employeeDao.updateEmployee(employee);
                    updated++;

                    emailService.sendmail(
                            employee.getMail(),
                            "Employee Details Updated",
                            "Hi " + employee.getName() + ",\n\nYour details have been successfully updated in the system.\n\nThank you!"
                    );

                } catch (EmptyResultDataAccessException e) {
                    employeeDao.addEmployee(employee);
                    added++;

                    // Send email for addition
                    emailService.sendmail(
                            employee.getMail(),
                            "Welcome to the Company",
                            "Hi " + employee.getName() + ",\n\nWelcome to the company! Your details have been successfully added to the system.\n\nThank you!"
                    );

                }
                int role_id = (employee.getEmp_id() == 2001) ? 3000 : 1000;
                roleMappingDao.addRoleMapping(employee.getEmp_id(), role_id);
            }
        }

        return Map.of(
                "message", "File processed successfully",
                "added", added,
                "updated", updated,
                "skipped", skipped
        );
    }

    public String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        }

        else if (cell.getCellType() == CellType.NUMERIC) {

            return String.valueOf((long) cell.getNumericCellValue());
        }
        else {
            return "";
        }
    }




    public Map<String, Object> updateEmail(int empId, String email) {
        Employee employee = employeeDao.getEmployee(empId);
        if (employee == null) {
            throw new RuntimeException("Employee not found for empId: " + empId);
        }
        employeeDao.updateEmployeeEmail(empId, email);
        return Map.of("message", "Email updated successfully", "empId", empId, "email", email);
    }




    @Override
    public Map<String, Object> addStock(Stock stock) {
        return stockFeignClient.addStock(stock).getBody();
    }

    @Override
    public Map<String, Object> getStockByCategory(String category) {
        return stockFeignClient.getStockByCategory(category).getBody();
    }


    public Mono<Map> getStockByCategorythorughwebClient(String category) {
        return webclientService.getStockByCategorythorughwebClient(category);  // Call WebClient service to get stock data by category
    }



}



