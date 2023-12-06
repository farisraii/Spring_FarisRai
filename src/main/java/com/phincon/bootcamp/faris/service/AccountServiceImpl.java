package com.phincon.bootcamp.faris.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.phincon.bootcamp.faris.model.AccountLatihan;
import com.phincon.bootcamp.faris.repository.AccountRepository;


@Service
public class AccountServiceImpl implements AccountService {
    
    @Autowired
    AccountRepository repository;

    @Override
    public AccountLatihan geAccount() {
        AccountLatihan acc = new AccountLatihan();
        acc.setId(1);
        acc.setName("Faris");
        acc.setAmount(10.0);

        return acc;
    }

    @Override
    public List<AccountLatihan> getAccounts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccounts'");
    }

    @Override
    public AccountLatihan save(AccountLatihan acc) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Optional<AccountLatihan> findByID(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByID'");
    }

 }
    