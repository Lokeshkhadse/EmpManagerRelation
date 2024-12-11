package com.example.Incentive.Dao;

import com.example.Incentive.Dto.IncentiveDto;
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





        return incentiveDto;
    }

    public Stock getstock(Stock stock){
        String sql = "select * from stock  where id=?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Stock.class),stock.getId());
    }

//    public int updateQuantity(Stock stock, int updatedQuantity) {
//        String sql = "update stock set quantity = ? where id = ?";
//        return jdbcTemplate.update(sql, updatedQuantity, stock.getId());
//    }

}
