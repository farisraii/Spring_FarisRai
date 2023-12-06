package com.phincon.bootcamp.faris.service;

import java.util.List;
import java.util.Optional;

import com.phincon.bootcamp.faris.model.AccountLatihan;

public interface AccountService {
    
    public AccountLatihan geAccount();

    public List <AccountLatihan> getAccounts();

    public AccountLatihan save (AccountLatihan acc);

    public Optional <AccountLatihan> findByID (int id);

}