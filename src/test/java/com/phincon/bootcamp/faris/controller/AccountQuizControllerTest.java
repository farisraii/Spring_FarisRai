package com.phincon.bootcamp.faris.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phincon.bootcamp.faris.model.Account;
import com.phincon.bootcamp.faris.service.AccountQuizService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountQuizController.class)
public class AccountQuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AccountQuizService accountService;

    private Account account;

    @BeforeEach
    public void setUp() {
        account = new Account();
        account.setId(1L);
        account.setType("Savings");
        account.setCustomerId(123L);
        account.setAmount(1000L);
        account.setStatus(true);
        account.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        account.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void testGetAllAccounts() throws Exception {
        when(accountService.getAllAccounts()).thenReturn(Arrays.asList(account));

        mockMvc.perform(get("/accounts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("Savings"));
    }

    @Test
    public void testGetAccountById() throws Exception {
        when(accountService.getAccountById(anyLong())).thenReturn(Optional.of(account));

        mockMvc.perform(get("/accounts/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Savings"));
    }

    @Test
    public void testCreateAccount() throws Exception {
        when(accountService.createAccount(any(Account.class))).thenReturn(account);

        mockMvc.perform(post("/accounts")
                .content(objectMapper.writeValueAsString(account))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.type").value("Savings"));
    }

    @Test
    public void testUpdateAccount() throws Exception {
        when(accountService.updateAccount(anyLong(), any(Account.class))).thenReturn(account);

        mockMvc.perform(put("/accounts/{id}", 1)
                .content(objectMapper.writeValueAsString(account))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Savings"));
    }

    @Test
    public void testDeleteAccount() throws Exception {
        mockMvc.perform(delete("/accounts/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testPatchAccount() throws Exception {
        when(accountService.patchAccount(anyLong(), anyMap())).thenReturn(account);

        Map<String, Object> updates = Map.of("type", "Savings");

        mockMvc.perform(patch("/accounts/{id}", 1)
                .content(objectMapper.writeValueAsString(updates))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value("Savings"));
    }

    @Test
    public void testPatchAccountNotFound() throws Exception {
        when(accountService.patchAccount(anyLong(), anyMap())).thenReturn(null);

        Map<String, Object> updates = Map.of("type", "Savings");

        mockMvc.perform(patch("/accounts/{id}", 1)
                .content(objectMapper.writeValueAsString(updates))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateAccountNotFound() throws Exception {
        when(accountService.updateAccount(anyLong(), any(Account.class))).thenReturn(null);

        mockMvc.perform(put("/accounts/{id}", 1)
                .content(objectMapper.writeValueAsString(account))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAccountByIdNotFound() throws Exception {
        when(accountService.getAccountById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/accounts/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
