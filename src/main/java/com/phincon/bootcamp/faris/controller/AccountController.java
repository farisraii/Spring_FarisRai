package com.phincon.bootcamp.faris.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.phincon.bootcamp.faris.model.Account;
import com.phincon.bootcamp.faris.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private static final Logger log = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable String id) {
        log.info("Request received for account ID: {}", id);
        
        Account account = accountService.getAccountById(id);

        if (account != null) {
            log.info("Account found with ID: {}", id);
            return ResponseEntity.ok(account);
        } else {
            log.warn("Account not found with ID: {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}
