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

import com.phincon.bootcamp.faris.model.AccountLatihan;
import com.phincon.bootcamp.faris.service.AccountService;

@RestController
public class AccountController {

    @Autowired
    AccountService asd;
    
    @GetMapping ("/bootcamp/account")
    public AccountLatihan getAccount(){
        return asd.geAccount();
    }

    @GetMapping ("/bootcamp/accounts")
    public List<AccountLatihan> getAccounts(){
        return asd.getAccounts();
    }

    @PostMapping ("/bootcamp/account")
    public AccountLatihan save(@RequestBody Map<String, Object> body){
        AccountLatihan acc = new AccountLatihan();
        acc.setId((int) body.get("id"));
        acc.setName((String) body.get("name"));
        acc.setAmount((double) body.get("amount"));

        return asd.save(acc);
    }

    @GetMapping("/bootcamp/account/{id}")
    public Optional<AccountLatihan> findByID (@PathVariable int id) {
        return asd.findByID(id);
    }
    
}
