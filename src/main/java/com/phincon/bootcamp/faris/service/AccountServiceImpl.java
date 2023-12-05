package com.phincon.bootcamp.faris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.bootcamp.faris.model.Account;
import com.phincon.bootcamp.faris.repository.AccountRepository;


@Service
public class AccountServiceImpl implements AccountService {
    
    @Autowired
    AccountRepository repository;

    @Override
    public Account geAccount() {
        Account acc = new Account();
        acc.setId(1);
        acc.setName("Faris");
        acc.setAmount(10.0);

        return acc;
    }

    @Override
    public List<Account> getAccounts() {
        return (List <Account>) repository.findAll();
    }

    @Override
    public Account save (Account account){
        return repository.save(account);
    }
    
}