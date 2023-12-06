package com.phincon.bootcamp.faris.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phincon.bootcamp.faris.model.Customer;
import com.phincon.bootcamp.faris.service.CustomerQuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerQuizController.class)
public class CustomerQuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerQuizService customerService;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setAge(30);
        customer.setStatus(true);
        customer.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        customer.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void testGetAllCustomers() throws Exception {
        List<Customer> customers = Arrays.asList(customer);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/customers/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        when(customerService.getCustomerById(anyLong())).thenReturn(Optional.of(customer));

        mockMvc.perform(get("/api/customers/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testCreateCustomer() throws Exception {
        when(customerService.createCustomer(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(post("/api/customers/create")
                .content(objectMapper.writeValueAsString(customer))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        when(customerService.updateCustomer(anyLong(), any(Customer.class))).thenReturn(customer);

        mockMvc.perform(put("/api/customers/update/{id}", 1)
                .content(objectMapper.writeValueAsString(customer))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        doNothing().when(customerService).deleteCustomer(anyLong());

        mockMvc.perform(delete("/api/customers/delete/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testPatchCustomer() throws Exception {
    Customer updatedCustomer = new Customer();
    updatedCustomer.setId(1L);
    updatedCustomer.setName("John Updated");
    // Anda bisa mengatur nilai lain jika diperlukan

    when(customerService.patchCustomer(anyLong(), anyMap())).thenReturn(updatedCustomer);

    Map<String, Object> updates = Map.of("name", "John Updated");

    mockMvc.perform(patch("/api/customers/patch/{id}", 1)
            .content(objectMapper.writeValueAsString(updates))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("John Updated"));
    }

    @Test
    public void testPatchCustomerNotFound() throws Exception {
    when(customerService.patchCustomer(anyLong(), anyMap())).thenReturn(null);

    Map<String, Object> updates = Map.of("name", "John Updated");

    mockMvc.perform(patch("/api/customers/patch/{id}", 1)
            .content(objectMapper.writeValueAsString(updates))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateCustomerNotFound() throws Exception {
    when(customerService.updateCustomer(anyLong(), any(Customer.class))).thenReturn(null);

    mockMvc.perform(put("/api/customers/update/{id}", 1)
            .content(objectMapper.writeValueAsString(customer))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void testGetCustomerByIdNotFound() throws Exception {
    when(customerService.getCustomerById(anyLong())).thenReturn(Optional.empty());

    mockMvc.perform(get("/api/customers/{id}", 1)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
        }




}
