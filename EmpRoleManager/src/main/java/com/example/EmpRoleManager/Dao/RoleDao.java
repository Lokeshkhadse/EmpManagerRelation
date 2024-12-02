package com.example.EmpRoleManager.Dao;

import com.example.EmpRoleManager.Entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Role addRole(Role role) {
        String sql = "INSERT INTO role (role_id, designation) VALUES (?, ?)";
        jdbcTemplate.update(sql, role.getRole_id(), role.getDesignation());
        return role;
    }



}
