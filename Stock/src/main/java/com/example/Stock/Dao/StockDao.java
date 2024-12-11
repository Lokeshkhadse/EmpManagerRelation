package com.example.Stock.Dao;

import com.example.Stock.Entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StockDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Stock addStock(Stock stock) {
        String sql = "insert into stock(category,dress_name,quantity,price)Values(?,?,?,?) ";
        jdbcTemplate.update(sql,stock.getCategory(),stock.getDressName(),stock.getQuantity(),stock.getPrice());
        return stock;
    }

    public List<Stock> getAllStock() {
        String sql = "select * from stock";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Stock.class));
    }

    public List<Stock> getStockCategory(String category) {
        String sql = "select * from stock where category = ?";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Stock.class),category);
    }

    public Stock getStockByid(int id) {
        String sql = "select * from stock where id = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(Stock.class),id);
    }

    public int updateStock(int id, int quantity) {
        String updatequery = "update stock set quantity = ? where id=?";
        return jdbcTemplate.update(updatequery,quantity,id);

    }
}

