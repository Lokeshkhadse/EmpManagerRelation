package com.example.EmpRoleManager.Dao;

import com.example.EmpRoleManager.Entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    public Role findById(int role_id) {
        String sql = "SELECT * FROM role WHERE role_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{role_id}, new BeanPropertyRowMapper<>(Role.class));
        } catch (EmptyResultDataAccessException e) {
            return null; // Return null if no role found
        }
    }



}
