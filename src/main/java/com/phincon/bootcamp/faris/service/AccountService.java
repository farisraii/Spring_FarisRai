package com.phincon.bootcamp.faris.service;

import java.util.List;

import com.phincon.bootcamp.faris.model.Account;

public interface AccountService {
    
    public Account geAccount();

    public List <Account> getAccounts();

    public Account save (Account account);
}