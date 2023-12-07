package com.phincon.bootcamp.faris.service;

import com.phincon.bootcamp.faris.model.Account;
import com.phincon.bootcamp.faris.model.AccountDto;

import java.util.List;

public interface AccountService {
    public Account getAccount(String id);

    public List<Account> getAccounts();

    public Account save(AccountDto accountDto);

    public Account patch(AccountDto accountDto);

    public Account update(AccountDto accountDto);

    public Account delete(String id);

}