package com.phincon.bootcamp.faris.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phincon.bootcamp.faris.model.Customer;
import com.phincon.bootcamp.faris.repository.CustomerQuizRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerQuizServiceImpl implements CustomerQuizService {

    private final CustomerQuizRepository customerRepository;

    @Autowired
    public CustomerQuizServiceImpl(CustomerQuizRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        if (customerRepository.existsById(id)) {
            customer.setId(id);
            return customerRepository.save(customer);
        }
        return null; // Customer not found
    }

    @Override
    public Customer patchCustomer(Long id, Map<String, Object> updates) {
        Optional<Customer> existingCustomerOptional = customerRepository.findById(id);
        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();

            updates.forEach((key, value) -> {
                switch (key) {
                    case "name":
                        if (value instanceof String) {
                            existingCustomer.setName((String) value);
                        } else {
                            throw new IllegalArgumentException("Invalid data type for 'name' field");
                        }
                        break;
                    case "age":
                        if (value instanceof Integer) {
                            existingCustomer.setAge((Integer) value);
                        } else {
                            throw new IllegalArgumentException("Invalid data type for 'age' field");
                        }
                        break;
                    // Optionally handle other specific fields here
                }
            });

            return customerRepository.save(existingCustomer);
        }
        return null;
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
