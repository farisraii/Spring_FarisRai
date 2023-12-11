package com.phincon.bootcamp.faris.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.phincon.bootcamp.faris.model.Customer;

@Repository
public interface CustomerQuizRepository extends JpaRepository<Customer, Long> {
}