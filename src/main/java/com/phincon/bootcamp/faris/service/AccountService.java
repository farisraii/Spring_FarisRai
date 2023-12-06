package com.phincon.bootcamp.faris.service;

import java.util.List;
import java.util.Optional;

import com.phincon.bootcamp.faris.model.Account;

public interface AccountService {
    
    public Account geAccount();

    public List <Account> getAccounts();

    public Account save (Account account);

    public Optional <Account> findByID (int id);

}