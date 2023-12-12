package com.phincon.bootcamp.faris.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.phincon.bootcamp.faris.model.Customer;

@Repository
public interface CustomerQuizRepository extends CrudRepository<Customer, String> {
}