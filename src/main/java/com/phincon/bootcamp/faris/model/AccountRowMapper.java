package com.phincon.bootcamp.faris.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    @Nullable
    public Account mapRow(ResultSet rs, int rn) throws SQLException {
        Account acc = new Account();
        acc.setId(rs.getString("id"));
        acc.setCustomerId(rs.getString("customer_id"));
        acc.setName(rs.getString("name"));
        acc.setType(rs.getString("type"));
        acc.setStatus(rs.getString("status"));
        acc.setAmount(rs.getLong("amount"));
        acc.setCreatedDate(rs.getTimestamp("created_date"));
        return acc;
        
    }
    
}
