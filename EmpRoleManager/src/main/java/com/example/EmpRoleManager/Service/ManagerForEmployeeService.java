package com.example.EmpRoleManager.Service;

import com.example.EmpRoleManager.Entity.ManagerForEmployee;

import java.util.List;
import java.util.Map;

public interface ManagerForEmployeeService {

    ManagerForEmployee addRelation(int managerId, int empId);

    Map<String, Object> gethierarchy(int empId);


    // public List<EmployeeHierarchyDto> getHierarchy(int empId);


}

