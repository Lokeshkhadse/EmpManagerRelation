package com.example.EmpRoleManager.Dao;

import com.example.EmpRoleManager.Entity.Role;
import com.example.EmpRoleManager.Entity.RoleMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RoleMappingDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private EmployeeDao employeeDao;

    public void addRoleMapping(int emp_id, int role_id) {
        String sql = "INSERT INTO role_mapping(emp_id, role_id) VALUES(?, ?)";
        jdbcTemplate.update(sql, emp_id, role_id);
    }

    public RoleMapping getRoleMapping(int emp_id) {
        String sql = "Select * from role_mapping where emp_id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{emp_id},new BeanPropertyRowMapper<>(RoleMapping.class));
    }


    public RoleMapping findByEmployee(int empId) {
        String query="select * from role_mapping where emp_id=?";
        return  jdbcTemplate.queryForObject(query,new BeanPropertyRowMapper<>(RoleMapping.class),empId);
    }

    public int updateRoleForEmployee(RoleMapping roleMapping) {
        String query="update role_mapping set role_id=? where rolemap_id=?";
       return  jdbcTemplate.update(query,roleMapping.getRole().getRole_id(),roleMapping.getRolemap_id());
    }

    public boolean checkisManager(int empId) {
        String sql = """
        SELECT COUNT(*) 
        FROM role_mapping 
        WHERE emp_id = ? 
          AND role_id IN (2000, 3000, 4000)
    """;

        Integer count = jdbcTemplate.queryForObject(sql, new Object[]{empId}, Integer.class);

        // Log the result for debugging
        System.out.println("Checking manager status for empId: " + empId);
        System.out.println("Manager status count: " + count);

        return count != null && count > 0;
    }

    public RoleMapping findemprolemapid(int empId) {
        String sql = "select rolemap_id from role_mapping where emp_id = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(RoleMapping.class),empId);
    }
    public int findByEmp(int empId) {
        String query = "SELECT rolemap_id FROM role_mapping WHERE emp_id = ?";
        return jdbcTemplate.queryForObject(query, Integer.class, empId);
    }

}
