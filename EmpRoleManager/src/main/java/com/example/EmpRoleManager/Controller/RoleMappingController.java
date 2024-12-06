package com.example.EmpRoleManager.Controller;

import com.example.EmpRoleManager.Entity.RoleMapping;
import com.example.EmpRoleManager.Service.RoleMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RoleMappingController {


    @Autowired
    private RoleMappingService roleMappingService;



    @PutMapping("/modify-role/{emp_id}/{role_id}")
    public ResponseEntity<Map<String,Object>> changeRole(@PathVariable int emp_id,@PathVariable int role_id){
        Map<String,Object> map=  this.roleMappingService.changeRole(emp_id,role_id);
        return ResponseEntity.ok().body(map);
    }


}
