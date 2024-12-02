package com.example.EmpRoleManager.Dao;

import com.example.EmpRoleManager.Entity.Employee;
import com.example.EmpRoleManager.Entity.Role;
import com.example.EmpRoleManager.Entity.RoleMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleMappingDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addRoleMapping(int emp_id, int role_id) {
        String sql = "INSERT INTO role_mapping(emp_id, role_id) VALUES(?, ?)";
        jdbcTemplate.update(sql, emp_id, role_id);
    }

    public RoleMapping getRoleMapping(int emp_id) {
        String sql = "Select * from role_mapping where emp_id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{emp_id},new BeanPropertyRowMapper<>(RoleMapping.class));
    }
}
