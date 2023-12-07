package com.phincon.bootcamp.faris.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.bootcamp.faris.model.Account;
import com.phincon.bootcamp.faris.model.AccountDto;
import com.phincon.bootcamp.faris.service.AccountService;

@RestController
@RequestMapping("/bootcamp")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/account/{id}")
    public Account getAccount(@PathVariable String id){
        return accountService.getAccount(id);
    }

    @GetMapping("/account")
    public List<Account> getAccounts(){
        return accountService.getAccounts();
    }

    @PostMapping("/account")
    public ResponseEntity<Account> save(@RequestBody AccountDto accountDto){
        HttpHeaders respheaders = new HttpHeaders();
        respheaders.set("MyResponeHeader", "MyValue");
        return new ResponseEntity<Account>(accountService.save(accountDto), respheaders, HttpStatus.CREATED);
    }

    @PutMapping("/account")
    public Account update(@RequestBody AccountDto accountDto){
        return accountService.update(accountDto);
    }

    @PatchMapping("/account")
    public Account patch(@RequestBody AccountDto accountDto){
        return accountService.patch(accountDto);
    }

    @DeleteMapping("/account/{id}")
    public Account delete(@PathVariable String id){
        return accountService.delete(id);
    }
}