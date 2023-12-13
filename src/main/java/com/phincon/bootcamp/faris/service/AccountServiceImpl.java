package com.phincon.bootcamp.faris.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.bootcamp.faris.model.Account;
import com.phincon.bootcamp.faris.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
    
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccountById(String id) {
        Account acc = accountRepository.getAccountByID(id);
        if (acc != null){
            return acc;
        }
        return new Account();
    }
}
