package com.example.EmpRoleManager.Service;

import com.example.EmpRoleManager.Entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RoleService {

    public List<Role> addRole(Role role);

}

