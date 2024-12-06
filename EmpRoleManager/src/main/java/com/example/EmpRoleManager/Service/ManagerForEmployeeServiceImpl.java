package com.example.EmpRoleManager.Service;

import com.example.EmpRoleManager.Dao.EmployeeDao;
import com.example.EmpRoleManager.Dao.ManagerForEmployeeDao;
import com.example.EmpRoleManager.Dao.RoleMappingDao;
import com.example.EmpRoleManager.Entity.Employee;
import com.example.EmpRoleManager.Entity.ManagerForEmployee;
import com.example.EmpRoleManager.Entity.RoleMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public Map<String, Object> gethierarchy(int empId) {
        return gethierarchy(empId, new HashSet<>());
    }

    public Map<String, Object> gethierarchy(int empId, Set<Integer> set) {
        Map<String, Object> map = new HashMap<>();

        // Avoid cycles by checking if empId is already in the set
        if (set.contains(empId)) {
            return map;
        }

        // Add empId to the set to track the visited employees
        set.add(empId);

        Employee employee = this.employeeDao.getEmployee(empId);
        if (employee == null) {
            throw new RuntimeException("Employee not found for empId: " + empId);
        }
        System.out.println("employee Object: " + employee);

        int roleMapId = this.roleMappingDao.findByEmp(empId);
        System.out.println("roleMapping Object: " + roleMapId);

        if (roleMapId == 0) {
            throw new RuntimeException("roleMapping is null");
        }

        List<Integer> employeesUnderManagerList = this.managerForEmployeeDao.findEmpsUnderManager(roleMapId);
        System.out.println("employeesUnderManagerList: " + employeesUnderManagerList);

        List<Map<String, Object>> list = new ArrayList<>();
        for (var employees : employeesUnderManagerList) {
            Map<String, Object> subHierarchy = gethierarchy(employees, set);
//            System.out.println("subHierarchy: " + subHierarchy);

            list.add(subHierarchy);
        }

        Map<String, Object> managersMap = new HashMap<>();
        managersMap.put("empId", employee.getEmp_id());
        managersMap.put("empName", employee.getName());
        managersMap.put("phoneNumber", employee.getPhno());
        managersMap.put("reporting Employees", list);



        map.put("message", "fetched successfully");
        map.put("status", HttpStatus.OK.value());
        map.put("result", managersMap);



        set.remove(empId);

        return map;
    }


//    @Override
//    public List<EmployeeHierarchyDto> getHierarchy(int empId) {
//        List<EmployeeHierarchyDto> hierarchyList = new ArrayList<>();
//
//        Employee manager = employeeDao.getEmployee(empId);
//        if (manager != null) {
//            hierarchyList.add(new EmployeeHierarchyDto(manager.getEmp_id(), manager.getName(), manager.getPhno()));
//        } else {
//            return hierarchyList;
//        }
//
//        RoleMapping roles = roleMappingDao.getRoleMapping(empId);
//
//
//        List<Integer> employeeIdsUnderManager = managerForEmployeeDao.getHierarchy(empId);
//
//        for (Integer empUnderManager : employeeIdsUnderManager) {
//            Employee employee = employeeDao.getEmployee(empUnderManager);
//            if (employee != null) {
//                hierarchyList.add(new EmployeeHierarchyDto(employee.getEmp_id(), employee.getName(), employee.getPhno()));
//            }
//        }
//
//        return hierarchyList;
//    }




}
