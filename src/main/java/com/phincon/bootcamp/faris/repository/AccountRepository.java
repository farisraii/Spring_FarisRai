package com.phincon.bootcamp.faris.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.phincon.bootcamp.faris.model.Account;

@Repository

public interface AccountRepository {

    Account getAccountByID(String id);

    Account getAccountByIdandName(String id, String name);

    Account deleteAccount (String id);

    Account updateAccount (String id, Account account);

    Account createAccount (Account account);

    List<Account> getAllList ();
}
