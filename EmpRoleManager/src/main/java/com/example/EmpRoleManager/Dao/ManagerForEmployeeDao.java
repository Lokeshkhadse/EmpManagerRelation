package com.example.EmpRoleManager.Dao;

import com.example.EmpRoleManager.Entity.Employee;
import com.example.EmpRoleManager.Entity.ManagerForEmployee;
import com.example.EmpRoleManager.Entity.RoleMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class ManagerForEmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RoleMappingDao roleMappingDao;

    public ManagerForEmployee addRelation(ManagerForEmployee managerForEmployee1) {
        String sql = "insert into manager_for_employee (emp_id , rolemap_id) values(?,?)";

        jdbcTemplate.update(sql, managerForEmployee1.getEmployee().getEmp_id(), managerForEmployee1.getRoleMapping().getRolemap_id());
        return managerForEmployee1;
    }

    public List<Integer> getHierarchy(int empId) {
        String sql = "SELECT emp_id FROM manager_for_employee WHERE rolemap_id = (SELECT rolemap_id FROM role_mapping  WHERE emp_id = ?)";
        return jdbcTemplate.queryForList(sql, Integer.class, empId);
    }


//    public List<ManagerForEmployee> findEmployeeunderManager(int empId) {
//        RoleMapping roleMapping = roleMappingDao.findemprolemapid(empId);
//
//        //String sql = "select emp_id from manager_for_employee where rolemap_id = ?";
//        //  return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(ManagerForEmployee.class),roleMapping.getRolemap_id());
//
//        // Query to join manager_for_employee and employee tables
//        String sql = """
//        SELECT m.man_emp_id ,m.rolemap_id, e.emp_id, e.name, e.phno
//        FROM manager_for_employee m
//        INNER JOIN employee e ON m.emp_id = e.emp_id
//        WHERE m.rolemap_id = ?
//    """;
//
//        return jdbcTemplate.query(sql, new Object[]{roleMapping.getRolemap_id()}, (rs, rowNum) -> {
//            ManagerForEmployee managerForEmployee = new ManagerForEmployee();
//
//            managerForEmployee.setManEmp_id(rs.getInt("man_emp_id"));
//
//         //    Set RoleMapping
//            RoleMapping rm = new RoleMapping();
//            rm.setRolemap_id(rs.getInt("rolemap_id"));
//            managerForEmployee.setRoleMapping(rm);
//
//            // Set Employee details
//            Employee employee = new Employee();
//            employee.setEmp_id(rs.getInt("emp_id"));
//            employee.setName(rs.getString("name"));
//            employee.setPhno(rs.getString("phno"));
//            managerForEmployee.setEmployee(employee);
//
//            return managerForEmployee;
//        });
//    }




    public List<Integer>
    findEmpsUnderManager(int rolemapId) {
        String query = "SELECT emp_id FROM manager_for_employee WHERE rolemap_id = ?";
        return jdbcTemplate.queryForList(query, Integer.class, rolemapId);
    }


}
