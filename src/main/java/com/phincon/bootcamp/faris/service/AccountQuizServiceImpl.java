package com.phincon.bootcamp.faris.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.bootcamp.faris.model.Account;
import com.phincon.bootcamp.faris.repository.AccountQuizRepository;

@Service
public class AccountQuizServiceImpl implements AccountQuizService {

    private final AccountQuizRepository accountRepository;

    @Autowired
    public AccountQuizServiceImpl(AccountQuizRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> getAllAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    @Override
    public Optional<Account> getAccountById(String id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account updateAccount(String id, Account account) {
        if (accountRepository.existsById(id)) {
            account.setId(id);
            return accountRepository.save(account);
        }
        return null;
    }

    @Override
    public void deleteAccount(String id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account patchAccount(String id, Map<String, Object> updates) {
        Optional<Account> existingAccountOptional = accountRepository.findById(id);
        if (existingAccountOptional.isPresent()) {
            Account existingAccount = existingAccountOptional.get();

            updates.forEach((key, value) -> {
                switch (key) {
                    case "type":
                        existingAccount.setType((String) value);
                        break;
                    case "customerId":
                        existingAccount.setCustomerId((String) value);
                        break;
                    case "amount":
                        existingAccount.setAmount((Long) value);
                        break;
                    case "status":
                        existingAccount.setStatus((Boolean) value);
                        break;
                    default:
                        // Handle unknown keys or no operation required
                        break;
                }
            });

            return accountRepository.save(existingAccount);
        }
        return null;
    }

}
