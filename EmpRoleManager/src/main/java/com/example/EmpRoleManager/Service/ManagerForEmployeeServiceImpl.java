package com.example.EmpRoleManager.Service;

import com.example.EmpRoleManager.Dao.EmployeeDao;
import com.example.EmpRoleManager.Dao.ManagerForEmployeeDao;
import com.example.EmpRoleManager.Dao.RoleMappingDao;
import com.example.EmpRoleManager.Entity.Employee;
import com.example.EmpRoleManager.Entity.ManagerForEmployee;
import com.example.EmpRoleManager.Entity.RoleMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerForEmployeeServiceImpl implements ManagerForEmployeeService {

    @Autowired
    private ManagerForEmployeeDao managerForEmployeeDao;

    @Autowired
    private RoleMappingDao roleMappingDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public ManagerForEmployee addRelation(int managerId, int empId) {
        RoleMapping roleMapping = roleMappingDao.getRoleMapping(managerId);
        Employee employee = employeeDao.getEmployee(empId);

        ManagerForEmployee managerForEmployee1 = new ManagerForEmployee();
        managerForEmployee1.setEmployee(employee);
        managerForEmployee1.setRoleMapping(roleMapping);

        return managerForEmployeeDao.addRelation(managerForEmployee1);

    }

    @Override
    public Map<String, Object> getHierarchy(int empId) {
        Map<String, Object> response = new HashMap<>();

        Employee employee = employeeDao.getEmployee(empId);
        RoleMapping roleMapping = roleMappingDao.getRoleMapping(empId);

        if (employee != null) {
            response.put("manager_id", empId);
            response.put("name", employee.getName());
            response.put("phno", employee.getPhno());
        } else {
            response.put("manager_id", empId);
            response.put("name", "Not found");
            response.put("phno", "Not found");
        }

        List<Integer> employeeIdsUnderManager = managerForEmployeeDao.getHierarchy(empId);

        response.put("employees", employeeIdsUnderManager);

        return response;
    }

//    @Override
//    public List<ManagerForEmployee> getHierarchy(int emp_id) {
//
//        List<ManagerForEmployee> processedList = new ArrayList<>();
//
//
//        Employee employee = employeeDao.getEmployee(emp_id);
//        RoleMapping roleMapping = roleMappingDao.getRoleMapping(emp_id);
//
//        List<ManagerForEmployee>list = managerForEmployeeDao.getHierarchy(roleMapping.getRolemap_id());
//
//        for(ManagerForEmployee e : list){
//            processedList.add(e);
//        }
//
//        return managerForEmployeeDao.getHierarchy(emp_id);
//
//    }
}
