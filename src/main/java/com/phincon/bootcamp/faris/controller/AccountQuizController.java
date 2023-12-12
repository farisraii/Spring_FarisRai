package com.phincon.bootcamp.faris.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.phincon.bootcamp.faris.model.Account;
import com.phincon.bootcamp.faris.service.AccountQuizService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController


@RequestMapping("/accounts")
public class AccountQuizController {

    Logger log = LoggerFactory.getLogger(AccountQuizController.class);


    private final AccountQuizService accountService;

    public AccountQuizController(AccountQuizService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public Optional<Account> getAccountById(@PathVariable String id) {
        log.info("messege log");
        log.info("messegae {}", id);
        // log.error("fatal", arg:Throwable.class);
        log.debug("message {}", id);
        log.trace("message {}", id);

        return accountService.getAccountById(id);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable String id, @RequestBody Account account) {
        Account updatedAccount = accountService.updateAccount(id, account);
        return (updatedAccount != null) ? ResponseEntity.ok(updatedAccount) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id) {
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Account> patchAccount(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        Account patchedAccount = accountService.patchAccount(id, updates);
        return (patchedAccount != null) ? ResponseEntity.ok(patchedAccount) : ResponseEntity.notFound().build();
    }
}
