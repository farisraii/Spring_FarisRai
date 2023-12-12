package com.phincon.bootcamp.faris.service;

import com.phincon.bootcamp.faris.model.Customer;
import com.phincon.bootcamp.faris.repository.CustomerQuizRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerQuizServiceTest {

    @Mock
    private CustomerQuizRepository customerRepository;

    @InjectMocks
    private CustomerQuizServiceImpl customerService;

    private Customer testCustomer;

    @BeforeEach
    public void setup() {
        testCustomer = new Customer();
        testCustomer.setId("1L");
        testCustomer.setName("John Doe");
        testCustomer.setAge(30);
    }

    @Test
    public void testGetAllCustomers_WhenListNotEmpty() {
        List<Customer> customerList = Collections.singletonList(testCustomer);

        when(customerRepository.findAll()).thenReturn(customerList);

        List<Customer> fetchedCustomers = customerService.getAllCustomers();

        assertThat(fetchedCustomers).isNotNull();
        assertThat(fetchedCustomers.size()).isEqualTo(1);
        assertThat(fetchedCustomers.get(0)).isEqualTo(testCustomer);
    }

    @Test
    public void testGetAllCustomers_WhenListEmpty() {
        when(customerRepository.findAll()).thenReturn(Collections.emptyList());

        List<Customer> fetchedCustomers = customerService.getAllCustomers();

        assertThat(fetchedCustomers).isEmpty();
    }

    @Test
    public void testGetCustomerById() {
        when(customerRepository.findById("1L")).thenReturn(Optional.of(testCustomer));

        Optional<Customer> fetchedCustomer = customerService.getCustomerById("1L");

        assertThat(fetchedCustomer).isPresent();
        assertThat(fetchedCustomer.get()).isEqualTo(testCustomer);
    }

    @Test
    public void testCreateCustomer() {
        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);

        Customer createdCustomer = customerService.createCustomer(testCustomer);

        assertThat(createdCustomer).isEqualTo(testCustomer);
    }

    @Test
    public void testUpdateCustomer() {
        when(customerRepository.existsById("1L")).thenReturn(true);
        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);

        Customer updatedCustomer = customerService.updateCustomer("1L", testCustomer);

        assertThat(updatedCustomer).isEqualTo(testCustomer);
    }

    @Test
    public void testDeleteCustomer() {
        doNothing().when(customerRepository).deleteById("1L");

        customerService.deleteCustomer("1L");

        verify(customerRepository, times(1)).deleteById("1L");
    }

    @Test
    public void testPatchCustomer() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Jane Doe");
        updates.put("age", 35);

        when(customerRepository.findById("1L")).thenReturn(Optional.of(testCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);

        Customer patchedCustomer = customerService.patchCustomer("1L", updates);

        assertThat(patchedCustomer).isNotNull();
        assertThat(patchedCustomer.getName()).isEqualTo("Jane Doe");
        assertThat(patchedCustomer.getAge()).isEqualTo(35);
    }

    @Test
    public void testPatchCustomer_UnknownFields() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("address", "123 Main St");

        when(customerRepository.findById("1L")).thenReturn(Optional.of(testCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(testCustomer);

        Customer patchedCustomer = customerService.patchCustomer("1L", updates);

        assertThat(patchedCustomer).isNotNull();
        assertThat(patchedCustomer.getName()).isEqualTo("John Doe");
        assertThat(patchedCustomer.getAge()).isEqualTo(30); 
    }

    @Test
    public void testPatchCustomer_InvalidDataType() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("age", "thirty");

        when(customerRepository.findById("1L")).thenReturn(Optional.of(testCustomer));

        assertThrows(IllegalArgumentException.class, () -> customerService.patchCustomer("1L", updates));
    }

    @Test
    public void testPatchCustomerWithName() {

        Customer existingCustomer = new Customer();
        existingCustomer.setId("1L");
        existingCustomer.setName("Alice");
        existingCustomer.setAge(30);

        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Bob");

        Mockito.when(customerRepository.findById(anyString())).thenReturn(Optional.of(existingCustomer));
        Mockito.when(customerRepository.save(any(Customer.class))).thenReturn(existingCustomer);

        Customer patchedCustomer = customerService.patchCustomer("1L", updates);

        // Assertions
        assertThat(patchedCustomer).isNotNull();
        assertThat(patchedCustomer.getId()).isEqualTo(existingCustomer.getId());
        assertThat(patchedCustomer.getName()).isEqualTo("Bob");
        assertThat(patchedCustomer.getAge()).isEqualTo(existingCustomer.getAge());
    }

    @Test
    public void testPatchCustomerWithAge() {

        Customer existingCustomer = new Customer();
        existingCustomer.setId("1L");
        existingCustomer.setName("Alice");
        existingCustomer.setAge(30);

        Map<String, Object> updates = new HashMap<>();
        updates.put("age", 35);

        Mockito.when(customerRepository.findById(anyString())).thenReturn(Optional.of(existingCustomer));
        Mockito.when(customerRepository.save(any(Customer.class))).thenReturn(existingCustomer);

        Customer patchedCustomer = customerService.patchCustomer("1L", updates);

        assertThat(patchedCustomer).isNotNull();
        assertThat(patchedCustomer.getId()).isEqualTo(existingCustomer.getId());
        assertThat(patchedCustomer.getName()).isEqualTo(existingCustomer.getName());
        assertThat(patchedCustomer.getAge()).isEqualTo(35);
    }

    @Test
    public void testPatchCustomerWithInvalidDataType() {
        Customer existingCustomer = new Customer();
        existingCustomer.setId("1L");
        existingCustomer.setName("Alice");
        existingCustomer.setAge(30);

        Map<String, Object> updates = new HashMap<>();
        updates.put("age", "35"); 

        Mockito.when(customerRepository.findById(anyString())).thenReturn(Optional.of(existingCustomer));

        assertThatThrownBy(() -> customerService.patchCustomer("1L", updates))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid data type for 'age' field");
    }

    @Test
    public void testPatchCustomerWhenCustomerNotExists() {

        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "Alice");

        Mockito.when(customerRepository.findById(anyString())).thenReturn(Optional.empty());

        Customer patchedCustomer = customerService.patchCustomer("1L", updates);

        assertThat(patchedCustomer).isNull();
    }
    @Test
    public void testUpdateCustomerWhenCustomerNotExists() {

        Customer nonExistingCustomer = new Customer();
        nonExistingCustomer.setId("1L");
        nonExistingCustomer.setName("John Doe");

        Mockito.when(customerRepository.existsById(anyString())).thenReturn(false);

        Customer updatedCustomer = customerService.updateCustomer("1L", nonExistingCustomer);

        assertThat(updatedCustomer).isNull();
    }

    @Test
    public void testPatchCustomerWithInvalidNameType() {
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", 123);

        Customer existingCustomer = new Customer();
        existingCustomer.setId("1L");

        Mockito.when(customerRepository.findById(anyString())).thenReturn(Optional.of(existingCustomer));

        assertThrows(IllegalArgumentException.class, () -> customerService.patchCustomer("1L", updates));
    }

}