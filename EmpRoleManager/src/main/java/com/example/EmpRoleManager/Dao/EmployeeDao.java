package com.example.EmpRoleManager.Dao;

import com.example.EmpRoleManager.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Employee addEmployee(Employee employee) {

        String sql = "INSERT INTO employee(emp_id, name, phno,mail) VALUES (?, ?, ?,?)";
        jdbcTemplate.update(sql, employee.getEmp_id(), employee.getName(), employee.getPhno(),employee.getMail());
        return employee;

    }


    public Employee getEmployee(int emp_id) {
        String sql = "SELECT * FROM employee WHERE emp_id = ?";

        return jdbcTemplate.queryForObject(sql,new Object[]{emp_id}, new BeanPropertyRowMapper<>(Employee.class));
    }

    public void updateEmployee(Employee employee){
        String sql = "update employee set name=? , phno = ? set mail= ? where emp_id=?";
        jdbcTemplate.update(sql,employee.getName(),employee.getPhno(),employee.getMail(),employee.getEmp_id());

    }


    public void updateEmployeeEmail(int empId, String email) {
        String sql = "UPDATE employee SET mail = ? WHERE emp_id = ?";
        jdbcTemplate.update(sql, email, empId);
    }


}


