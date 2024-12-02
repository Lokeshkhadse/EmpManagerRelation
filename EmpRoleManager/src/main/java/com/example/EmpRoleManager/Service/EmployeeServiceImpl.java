package com.example.EmpRoleManager.Service;

import com.example.EmpRoleManager.Dao.EmployeeDao;
import com.example.EmpRoleManager.Dao.RoleDao;
import com.example.EmpRoleManager.Dao.RoleMappingDao;
import com.example.EmpRoleManager.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements  EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private RoleMappingDao roleMappingDao;

    @Autowired
    private RoleDao roleDao;

    public Employee addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);

        int role_id = (employee.getEmp_id() == 10) ? 4000 : 1000;
        roleMappingDao.addRoleMapping(employee.getEmp_id(), role_id);

        return employee;

    }



}
