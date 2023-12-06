package com.phincon.bootcamp.faris.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.phincon.bootcamp.faris.model.Account;

public interface AccountQuizService {
    List<Account> getAllAccounts();
    Optional<Account> getAccountById(Long id);
    Account createAccount(Account account);
    Account updateAccount(Long id, Account account);
    void deleteAccount(Long id);
    Account patchAccount(Long id, Map<String, Object> updates);
}
