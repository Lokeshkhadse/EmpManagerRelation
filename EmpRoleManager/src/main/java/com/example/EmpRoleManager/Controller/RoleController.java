package com.example.EmpRoleManager.Controller;

import com.example.EmpRoleManager.Entity.Role;
import com.example.EmpRoleManager.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @PostMapping("/addroles")
    public ResponseEntity<List<Role>> addRole(Role role) {
        List<Role> addedRoles = roleService.addRole(role);
        return new ResponseEntity<>(addedRoles, HttpStatus.CREATED);
    }

}
