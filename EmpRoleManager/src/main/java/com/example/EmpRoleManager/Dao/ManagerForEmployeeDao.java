package com.example.EmpRoleManager.Dao;

import com.example.EmpRoleManager.Entity.ManagerForEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ManagerForEmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ManagerForEmployee addRelation(ManagerForEmployee managerForEmployee1) {
        String sql = "insert into manager_for_employee (emp_id , rolemap_id) values(?,?)";

        jdbcTemplate.update(sql, managerForEmployee1.getEmployee().getEmp_id(), managerForEmployee1.getRoleMapping().getRolemap_id());
        return managerForEmployee1;
    }
    
    public List<Integer> getHierarchy(int empId) {
        String sql = "SELECT emp_id FROM manager_for_employee WHERE rolemap_id = (SELECT rolemap_id FROM role_mapping  WHERE emp_id = ?)";
        return jdbcTemplate.queryForList(sql, Integer.class, empId);
    }
}
