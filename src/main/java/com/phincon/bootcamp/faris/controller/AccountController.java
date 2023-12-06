package com.phincon.bootcamp.faris.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.phincon.bootcamp.faris.model.Account;
import com.phincon.bootcamp.faris.service.AccountService;

@RestController
public class AccountController {

    @Autowired
    AccountService asd;
    
    @GetMapping ("/bootcamp/account")
    public Account getAccount(){
        return asd.geAccount();
    }

    @GetMapping ("/bootcamp/accounts")
    public List <Account>getAccounts(){
        return asd.getAccounts();
    }

    @PostMapping ("/bootcamp/account")
    public Account save(@RequestBody Map<String, Object> body){
        Account acc = new Account();
        acc.setId((int) body.get("id"));
        acc.setName((String) body.get("name"));
        acc.setAmount((double) body.get("amount"));

        return asd.save(acc);
    }

    @GetMapping("/bootcamp/account/{id}")
    public Optional<Account> findByID (@PathVariable int id) {
        return asd.findByID(id);
    }
    
}
