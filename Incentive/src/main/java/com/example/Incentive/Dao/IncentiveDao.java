package com.example.Incentive.Dao;

import com.example.Incentive.Dto.IncentiveDto;
import com.example.Incentive.Entity.Salary;
import com.example.Incentive.Entity.Stock;
import com.example.Incentive.ExternalService.StockService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class IncentiveDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StockService stockService;


    public IncentiveDto addIncentive(IncentiveDto incentiveDto) throws JsonProcessingException {

        double totalprice = 0.0;

        for(Stock stock : incentiveDto.getStocks()){

            Stock stockdetails = getstock(stock);

            if(stockdetails!=null){
                stock.setCategory(stockdetails.getCategory());
                stock.setDressName(stockdetails.getDressName());
                stock.setPrice(stockdetails.getPrice());
                totalprice = totalprice + stock.getQuantity() * stockdetails.getPrice();

            }
            System.out.println("stockDetails :"+ stockdetails.getQuantity());
            System.out.println("stock : "+stock.getQuantity());

           int updatedQuantity= stockdetails.getQuantity()- stock.getQuantity();
            System.out.println("updated Quantity : "+updatedQuantity);
             this.stockService.updateStock(stockdetails.getId(),updatedQuantity);



        }

        ObjectMapper objectMapper = new ObjectMapper();
        String stockjson = objectMapper.writeValueAsString(incentiveDto.getStocks());


        String query = "insert into incentive(emp_id,sale_amount,stock)values(?,?,?)";
        jdbcTemplate.update(query,incentiveDto.getEmp_id(),totalprice,stockjson);



        double quancalculation = totalprice * 0.02;
        System.out.println("quancalc1" + " " + quancalculation);


        String sql = "insert into salary(emp_id, salary, quancalculation, totalamount) values(?, ?, ?, ?)";
        jdbcTemplate.update(sql, incentiveDto.getEmp_id(), 0.0, quancalculation, 0.0);


        System.out.println("quancalc2" + " " + quancalculation);






        return incentiveDto;
    }

    public Stock getstock(Stock stock){
        String sql = "select * from stock  where id=?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Stock.class),stock.getId());
    }

    public Salary updatesalary(int id, int empId, Salary salary) {
        Salary existingSalary = getSalary(id, empId);

        System.out.println(existingSalary.getQuancalculation());
        System.out.println(salary.getSalary());

        double totalAmount = existingSalary.getQuancalculation() + salary.getSalary();

        String sql = "UPDATE salary SET salary = ?, totalamount = ? WHERE id = ? AND emp_id = ?";
        jdbcTemplate.update(sql, salary.getSalary(), totalAmount, id, empId);

        existingSalary.setSalary(salary.getSalary());
        existingSalary.setTotalamount(totalAmount);
        return existingSalary;
    }

    public Salary getSalary(int id, int empId) {
        String sql = "SELECT quancalculation FROM salary WHERE id = ? AND emp_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Salary.class), id, empId);
    }





//    public int updateQuantity(Stock stock, int updatedQuantity) {
//        String sql = "update stock set quantity = ? where id = ?";
//        return jdbcTemplate.update(sql, updatedQuantity, stock.getId());
//    }

}
