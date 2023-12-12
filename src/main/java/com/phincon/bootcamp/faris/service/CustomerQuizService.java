package com.phincon.bootcamp.faris.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.phincon.bootcamp.faris.model.Customer;

public interface CustomerQuizService {
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(String id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(String id, Customer customer);
    void deleteCustomer(String id);
    Customer patchCustomer(String id, Map<String, Object> updates);
}
