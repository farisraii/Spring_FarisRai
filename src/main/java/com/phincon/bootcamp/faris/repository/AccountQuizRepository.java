package com.phincon.bootcamp.faris.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.phincon.bootcamp.faris.model.Account;

@Repository
public interface AccountQuizRepository extends CrudRepository<Account, String> {
}
