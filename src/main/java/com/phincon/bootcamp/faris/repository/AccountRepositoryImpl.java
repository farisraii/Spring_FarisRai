package com.phincon.bootcamp.faris.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.phincon.bootcamp.faris.model.Account;

public class AccountRepositoryImpl implements AccountRepository {

        @Autowired
        JdbcTemplate jdbct;

        @Override
        public Account geAccountByID(String id) {
            String sql = "SELECT * FROM accounts WHERE id = ?";
            return jdbct.queryForObject(sql, Account.class, id);
        }
    
}