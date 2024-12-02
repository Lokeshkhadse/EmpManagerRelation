package com.example.EmpRoleManager.Controller;

import com.example.EmpRoleManager.Entity.ManagerForEmployee;
import com.example.EmpRoleManager.Service.ManagerForEmployeeService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ManagerForEmployeeController {

    @Autowired
    private ManagerForEmployeeService managerForEmployeeService;

    @PostMapping("/addrelation/{Manager_id}/{emp_id}")
    public ResponseEntity<String> addRelation(@PathVariable int Manager_id, @PathVariable int emp_id) {
        ManagerForEmployee managerForEmployee1 = managerForEmployeeService.addRelation(Manager_id, emp_id);
        String responseMessage = "Employee with ID " + emp_id + " is assigned to Manager with ID " + Manager_id;
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }
//
//    @GetMapping("/hierarchy/{emp_id}")
//    public ResponseEntity<List<ManagerForEmployee>> getHierarchy(@PathVariable int emp_id) {
//        List<ManagerForEmployee> list = managerForEmployeeService.getHierarchy(emp_id);
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }


    @GetMapping("/hierarchy/{emp_id}")
    public ResponseEntity<Map<String, Object>> getHierarchy(@PathVariable int emp_id) {
        Map<String, Object> response = managerForEmployeeService.getHierarchy(emp_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
