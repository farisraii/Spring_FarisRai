package com.phincon.bootcamp.faris;


import com.phincon.bootcamp.faris.controller.AccountController;
import com.phincon.bootcamp.faris.model.Account;
import com.phincon.bootcamp.faris.model.AccountDto;
import com.phincon.bootcamp.faris.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void testGetAccount() throws Exception {
        String accountId = "1";
        Account account = new Account();
        // Simulate account retrieval by service
        when(accountService.getAccount(accountId)).thenReturn(account);

        mockMvc.perform(get("/bootcamp/account/{id}", accountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testGetAccounts() throws Exception {
        List<Account> accounts = Arrays.asList(new Account(), new Account());
        // Simulate list of accounts retrieval by service
        when(accountService.getAccounts()).thenReturn(accounts);

        mockMvc.perform(get("/bootcamp/account"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testSave() throws Exception {
        AccountDto accountDto = new AccountDto();
        Account savedAccount = new Account();
        // Simulate account save by service
        when(accountService.save(accountDto)).thenReturn(savedAccount);

        mockMvc.perform(post("/bootcamp/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")) // Replace with your JSON payload
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testUpdate() throws Exception {
        AccountDto accountDto = new AccountDto();
        Account updatedAccount = new Account();
        // Simulate account update by service
        when(accountService.update(accountDto)).thenReturn(updatedAccount);

        mockMvc.perform(put("/bootcamp/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")) // Replace with your JSON payload
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testPatch() throws Exception {
        AccountDto accountDto = new AccountDto();
        Account patchedAccount = new Account();
        // Simulate account patch by service
        when(accountService.patch(accountDto)).thenReturn(patchedAccount);

        mockMvc.perform(patch("/bootcamp/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}")) // Replace with your JSON payload
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testDelete() throws Exception {
        String accountId = "1";
        Account deletedAccount = new Account();
        // Simulate account deletion by service
        when(accountService.delete(accountId)).thenReturn(deletedAccount);

        mockMvc.perform(delete("/bootcamp/account/{id}", accountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    Void testSavewithIdNull()throws Exception{
        AccountDto acc = new AccountDto();
        acc.setName(null);
        String requestBody = objectMapper.writ

        return null;

    }
}