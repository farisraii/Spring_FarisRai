package com.phincon.bootcamp.faris.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
        System.out.println("TEST");
        return asd.getAccounts();
    }

    @PostMapping ("/bootcamp/account")
    public Account save(@RequestBody Map<String, Object> body){
        Account acc = new Account();
        acc.setId((int) body.get("id"));
        acc.setAmount((double) body.get("amount"));
        acc.setName((String) body.get("name"));

        return asd.save(acc);
    }

}
