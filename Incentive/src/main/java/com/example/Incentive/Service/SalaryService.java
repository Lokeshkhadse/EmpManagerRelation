package com.example.Incentive.Service;

import com.example.Incentive.Dao.IncentiveDao;
import com.example.Incentive.Entity.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SalaryService {

    @Autowired
    private IncentiveDao incentiveDao;

    public Map<String, Object> updatesalary(int id, int empId, Salary salary) {
        Salary salary1 = incentiveDao.updatesalary(id,empId,salary);

        Salary salary2 = new Salary();
        salary2.setId(id);
        salary2.setEmp_id(empId);
        salary2.setSalary(salary1.getSalary());
        salary2.setQuancalculation(salary1.getQuancalculation());
        salary2.setTotalamount(salary1.getTotalamount());

        return Map.of(
                "status", HttpStatus.OK.value(),
                 "Success","sucessfully updated",
                  "data",salary2
        );
    }
}
