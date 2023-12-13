package com.phincon.bootcamp.faris.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.phincon.bootcamp.faris.model.Account;
import com.phincon.bootcamp.faris.model.AccountRowMapper;


@Repository

public class AccountRepositoryImpl implements AccountRepository {

    private static final String INACTIVE = "inactive";
    Logger logger = LoggerFactory.getLogger(AccountRepositoryImpl.class);
    
    @Autowired
    private final JdbcTemplate jdbcTemplate;
    
    public AccountRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Account getAccountByID(String id) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new AccountRowMapper(), new Object[]{id});

    }

    @Override
    public Account getAccountByIdandName(String id, String name) {
        String sql = "SELECT * FROM accounts WHERE id = ? AND name = ?";
        return jdbcTemplate.queryForObject(sql, new AccountRowMapper(), new Object[]{id, name});
    }

    @Override
    public Account deleteAccount(String id) {
        String sql = "UPDATE FROM accounts SET status = ? WHERE id = ?";
        int rowAffected = jdbcTemplate.update(sql, INACTIVE, id);
        logger.info("{} dengan {} telah berhasil dihapus", rowAffected, id);
        return getAccountByID(id);
    }

    @Override
    public Account updateAccount(String id, Account updatedAccount) {
    String sql = "UPDATE accounts SET customerId = ?, name = ?, type = ?, status = ?, amount = ?, createdDate = ?, updatedDate = ? WHERE id = ?";
    jdbcTemplate.update(
            sql,
            updatedAccount.getCustomerId(),
            updatedAccount.getName(),
            updatedAccount.getType(),
            updatedAccount.getStatus(),
            updatedAccount.getAmount(),
            updatedAccount.getCreatedDate(),
            updatedAccount.getUpdatedDate(),
            id
    );
    logger.info("Akun dengan ID {} telah berhasil diperbarui", id);
    return getAccountByID(id);
}


    @Override
    public Account createAccount(Account account) {
        String sql = "INSERT INTO accounts (id, customerId, name, type, status, amount, createdDate, updatedDate) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
            sql,
            account.getId(),
            account.getCustomerId(),
            account.getName(),
            account.getType(),
            account.getStatus(),
            account.getAmount(),
            account.getCreatedDate(),
            account.getUpdatedDate()
        );
        logger.info("Akun {} telah berhasil dibuat", account.getName());
        return account;
        }

    @Override
    public List<Account> getAllList() {
            String sql = "SELECT * FROM accounts";
            return jdbcTemplate.query(sql, new AccountRowMapper());
        }
}