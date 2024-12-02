package com.example.EmpRoleManager.Service;

import com.example.EmpRoleManager.Entity.ManagerForEmployee;

import java.util.List;
import java.util.Map;

public interface ManagerForEmployeeService {

    ManagerForEmployee addRelation( int managerId, int empId);

    //List<ManagerForEmployee> getHierarchy(int emp_id);


    Map<String, Object> getHierarchy(int empId);
}
