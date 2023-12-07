package com.phincon.bootcamp.faris.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.bootcamp.faris.model.Account;
import com.phincon.bootcamp.faris.model.AccountDto;
import com.phincon.bootcamp.faris.repository.AccountRepository;


@Service
public class AccountServiceImpl implements AccountService{

    private static String ACTIVE = "active";
    private static String DELETED = "deleted";

    @Autowired
    AccountRepository repository;

    @Override
    public Account getAccount(String id) {
        Optional<Account> acc = repository.findById(id);
        if (acc.isPresent()){
            return acc.get();
        }
        return new Account();
    }

    @Override
    public List<Account> getAccounts() {
        return (List<Account>) repository.findAll();
    }

    @Override
    public Account save(AccountDto accountDto){
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setCustomerId(accountDto.getCustomerId());
        account.setAmount(accountDto.getAmount());
        account.setName(accountDto.getName());
        account.setType(accountDto.getType());

        account.setStatus(ACTIVE);
        account.setCreateDate(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
        return repository.save(account);
    }

    @Override
    public Account update(AccountDto accountdDto){
        Optional<Account> acc = repository.findById(accountdDto.getId());
        if (acc.isPresent()){
            Account account = acc.get();
            account.setAmount(accountdDto.getAmount());
            account.setName(accountdDto.getName());
            account.setCustomerId(accountdDto.getCustomerId());
            account.setStatus(accountdDto.getStatus());

            account.setUpdatedDate(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
            return repository.save(account);
        }
        
        return new Account();
    }

    @Override
    public Account patch(AccountDto accountDto) {
        Optional<Account> acc = repository.findById(accountDto.getId());
        if (acc.isPresent()){
            Account account = acc.get();
            if (accountDto.getAmount() != null){
                account.setAmount(accountDto.getAmount());
            }
            if (accountDto.getName() != null){
                account.setName(accountDto.getName());
            }
            if (accountDto.getCustomerId() != null){
                account.setCustomerId(accountDto.getCustomerId());
            }
            if (accountDto.getStatus()!=null){
                account.setStatus(accountDto.getStatus());
            }

            account.setUpdatedDate(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
            return repository.save(account);
        }
        return new Account();
    }

    @Override
    public Account delete(String id) {
        Optional <Account> acc = repository.findById(id);
        if (acc.isPresent()){
            Account account = new Account();
            account.setStatus(DELETED);
            account.setUpdatedDate(new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis()));
            return repository.save(account);
        }
        return null;
        
    }
    
}
