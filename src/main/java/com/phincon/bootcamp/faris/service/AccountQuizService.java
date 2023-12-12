package com.phincon.bootcamp.faris.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.phincon.bootcamp.faris.model.Account;

public interface AccountQuizService {
    List<Account> getAllAccounts();
    Optional<Account> getAccountById(String id);
    Account createAccount(Account account);
    Account updateAccount(String id, Account account);
    void deleteAccount(String id);
    Account patchAccount(String id, Map<String, Object> updates);
}
