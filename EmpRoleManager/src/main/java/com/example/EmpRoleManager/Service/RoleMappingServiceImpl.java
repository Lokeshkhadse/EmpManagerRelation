package com.example.EmpRoleManager.Service;

import com.example.EmpRoleManager.Dao.EmployeeDao;
import com.example.EmpRoleManager.Dao.RoleDao;
import com.example.EmpRoleManager.Dao.RoleMappingDao;
import com.example.EmpRoleManager.Entity.Employee;
import com.example.EmpRoleManager.Entity.Role;
import com.example.EmpRoleManager.Entity.RoleMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoleMappingServiceImpl implements  RoleMappingService{

    @Autowired
    private RoleMappingDao roleMappingDao;
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private EmployeeDao employeeDao;


    @Override
    public Map<String, Object> changeRole(int empId, int roleId) {
        Map<String,Object> map=new HashMap<>();
        Employee employee= this.employeeDao.getEmployee(empId);
        if(employee==null)
        {
            throw new RuntimeException("employee is not present with that empId : "+empId);
        }
        Role role=this.roleDao.findById(roleId);
        if(role==null)
        {
            throw new RuntimeException("role doesnot found with that Id : "+roleId);
        }
       RoleMapping roleMapping= this.roleMappingDao.findByEmployee(empId);
        roleMapping.setRole(role);
        int rs= this.roleMappingDao.updateRoleForEmployee(roleMapping);
        if(rs>0)
        {

            map.put("status", HttpStatus.CREATED.value());
            map.put("message","Changed Role successfully");
        }

        return map;
    }




}
