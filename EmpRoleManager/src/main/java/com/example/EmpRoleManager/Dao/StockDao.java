//package com.example.EmpRoleManager.Dao;
//
//import com.example.EmpRoleManager.Entity.Stock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class StockDao {
//
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public Stock addStock(Stock stock) {
//        String sql = "insert into stock(category,dress_name,quantity)Values(?,?,?) ";
//        jdbcTemplate.update(sql,stock.getCategory(),stock.getDressName(),stock.getQuantity());
////        Stock stock1 = new Stock();
////        stock1.setId(stock.getId());
////        stock1.setCategory(stock.getCategory());
////        stock1.setDressName(stock.getDressName());
////        stock1.setQuantity(stock.getQuantity());
//        return stock;
//    }
//
//    public List<Stock> getAllStock() {
//        String sql = "select * from stock";
//        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Stock.class));
//    }
//
//    public List<Stock> getStockCategory(String category) {
//        String sql = "select * from stock where category = ?";
//        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Stock.class),category);
//    }
//}
