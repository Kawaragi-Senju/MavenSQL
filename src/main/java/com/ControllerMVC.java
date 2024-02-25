package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@Controller
public class ControllerMVC {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/batch")
    public String batch(){
        return "batch";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @PostMapping("/customers")
    public String request2(){
        Customer customer = new Customer(3, "Ivan", "adsdaf@gmnail.com");
        Customer customer1 = new Customer(4, "Jfsksd", "adsdaf@gmnail.com");
        Customer customer2 = new Customer(5, "Fkjdjfj", "asdsafasfaf@gmnail.com");
        ArrayList<Customer> arrayList = new ArrayList<>();
        arrayList.add(customer);
        arrayList.add(customer1);
        arrayList.add(customer2);
        String query = "INSERT INTO customers(id, name, email) VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(query, new PrepSt(arrayList));
        return "ok";
        //создать страницу где будет кнопка отправляющая пост запрос на /customers
    }

    @GetMapping("/customer/{id}")
    public String request(@PathVariable int id, Model model){
        Customer customer;
        String query = "SELECT * FROM customers WHERE id = ?";
        try{
            customer = jdbcTemplate.queryForObject(query,new Object[] {id} , new RowMap());
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