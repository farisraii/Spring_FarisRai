package com.phincon.bootcamp.faris.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerQuizModelTest {

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setAge(30);
        customer.setStatus(true);
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        customer.setCreatedDate(createdDate);
        customer.setUpdatedDate(createdDate);
    }

    @Test
    public void testCustomerModel() {
        assertThat(customer.getId()).isEqualTo(1L);
        assertThat(customer.getName()).isEqualTo("John Doe");
        assertThat(customer.getAge()).isEqualTo(30);
        assertThat(customer.isStatus()).isTrue();
        assertThat(customer.getCreatedDate()).isNotNull();
        assertThat(customer.getUpdatedDate()).isNotNull();
    }

    @Test
    public void testUpdatedCustomerModel() {
        Timestamp updatedDate = new Timestamp(System.currentTimeMillis());
        customer.setId(2L);
        customer.setName("Jane Doe");
        customer.setAge(25);
        customer.setStatus(false);
        customer.setUpdatedDate(updatedDate);

        assertThat(customer.getId()).isEqualTo(2L);
        assertThat(customer.getName()).isEqualTo("Jane Doe");
        assertThat(customer.getAge()).isEqualTo(25);
        assertThat(customer.isStatus()).isFalse();
        assertThat(customer.getUpdatedDate()).isEqualTo(updatedDate);
    }
}
