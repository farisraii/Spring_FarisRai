package com.phincon.bootcamp.faris.repository;

import org.springframework.data.repository.CrudRepository;

import com.phincon.bootcamp.faris.model.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    
}
