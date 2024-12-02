package com.example.EmpRoleManager.Service;

import com.example.EmpRoleManager.Dao.RoleDao;
import com.example.EmpRoleManager.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class RoleServiceImpl implements  RoleService{

    @Autowired
    private RoleDao roleDao;
    @Override
    public List<Role> addRole(Role role) {

        List<Role> addRolesList = List.of(
                new Role(1000, "Employee"),
                new Role(2000, "Employee, Manager"),
                new Role(3000, "Employee, Manager, HR"),
                new Role(4000, "Employee, Manager, Lead")
        );

        for (Role ro : addRolesList)
        {
            roleDao.addRole(ro);
        }
        return addRolesList;
    }

}
