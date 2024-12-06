package com.example.EmpRoleManager.Service;

import com.example.EmpRoleManager.Entity.RoleMapping;

import java.util.Map;

public interface RoleMappingService {



    Map<String, Object> changeRole(int empId, int roleId);
}
