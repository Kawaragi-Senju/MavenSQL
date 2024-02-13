package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.sql.*;
import java.util.Properties;

@Controller
public class ControllerMVC {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/customer/{id}")
    public String request(@PathVariable int id, Model model){
        Customer customer = new Customer();
        Properties prop = new Properties();
        String query = "SELECT * FROM customers WHERE id = ?";
        prop.put("user", "postgres");
        prop.put("password", "admin");
        try{
            customer = jdbcTemplate.queryForObject(query, new RowMap());
//            Class.forName("org.postgresql.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", prop);
//            PreparedStatement prst = conn.prepareStatement("SELECT * FROM customers WHERE id = ?");
//            prst.setInt(1, id);
//            ResultSet resSet = prst.executeQuery();
////            String sqlReq = "SELECT * FROM customers WHERE id = " +  id;
////            Statement st = conn.createStatement();
////            ResultSet resSet = st.executeQuery(sqlReq);
//            resSet.next();
//            customer.setId(resSet.getInt("id"));
//            customer.setEmail(resSet.getString("email"));
//            customer.setName(resSet.getString("name"));
           model.addAttribute("customer", customer);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
        return "Id";
    }
}