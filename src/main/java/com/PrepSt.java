package com;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrepSt implements BatchPreparedStatementSetter{
    ArrayList<Customer> arcust;

    public PrepSt(ArrayList<Customer> arcust){
        this.arcust = arcust;
    }

    @Override
    public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
        Customer customer = arcust.get(i);
        preparedStatement.setInt(1, customer.getId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getEmail());
    }

    @Override
    public int getBatchSize() {
        return arcust.size();
    }
}
