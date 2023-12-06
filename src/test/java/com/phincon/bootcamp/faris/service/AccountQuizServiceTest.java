package com.phincon.bootcamp.faris.service;

import com.phincon.bootcamp.faris.model.Account;
import com.phincon.bootcamp.faris.repository.AccountQuizRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
public class AccountQuizServiceTest {

    @Mock
    private AccountQuizRepository accountRepository;

    @InjectMocks
    private AccountQuizServiceImpl accountService;

    @Test
    public void testUpdateAccount() {
        Account existingAccount = new Account();
        existingAccount.setId(1L);
        existingAccount.setType("Savings");
        existingAccount.setCustomerId(123L);
        existingAccount.setAmount(1000L);
        existingAccount.setStatus(true);

        Mockito.when(accountRepository.existsById(anyLong())).thenReturn(true);
        Mockito.when(accountRepository.save(any(Account.class))).thenReturn(existingAccount);

        Account updatedAccount = accountService.updateAccount(1L, existingAccount);

        assertThat(updatedAccount).isNotNull();
        assertThat(updatedAccount.getId()).isEqualTo(existingAccount.getId());
        assertThat(updatedAccount.getType()).isEqualTo(existingAccount.getType());
        assertThat(updatedAccount.getCustomerId()).isEqualTo(existingAccount.getCustomerId());
        assertThat(updatedAccount.getAmount()).isEqualTo(existingAccount.getAmount());
        assertThat(updatedAccount.getStatus()).isEqualTo(existingAccount.getStatus());

        assertThat(updatedAccount).isNotNull();
        assertThat(updatedAccount.getId()).isEqualTo(1L);
    }

    @Test
    public void testPatchAccount() {
        Account existingAccount = new Account();
        existingAccount.setId(1L);
        existingAccount.setType("Savings");
        existingAccount.setCustomerId(123L);
        existingAccount.setAmount(1000L);
        existingAccount.setStatus(true);

        Map<String, Object> updates = new HashMap<>();
        updates.put("type", "Checking");
        updates.put("customerId", 456L);
        updates.put("amount", 2000L);
        updates.put("status", false);

        Mockito.when(accountRepository.findById(anyLong())).thenReturn(Optional.of(existingAccount));
        Mockito.when(accountRepository.save(any(Account.class))).thenReturn(existingAccount);

        Account patchedAccount = accountService.patchAccount(1L, updates);

        assertThat(patchedAccount).isNotNull();
        assertThat(patchedAccount.getId()).isEqualTo(existingAccount.getId());
        assertThat(patchedAccount.getType()).isEqualTo("Checking");
        assertThat(patchedAccount.getCustomerId()).isEqualTo(456L);
        assertThat(patchedAccount.getAmount()).isEqualTo(2000L);
        assertThat(patchedAccount.getStatus()).isEqualTo(false);
    }

    @Test
    public void testGetAllAccounts() {
        Account account1 = new Account();
        account1.setId(1L);
        Account account2 = new Account();
        account2.setId(2L);

        List<Account> accountList = new ArrayList<>();
        accountList.add(account1);
        accountList.add(account2);

        Mockito.when(accountRepository.findAll()).thenReturn(accountList);

        List<Account> fetchedAccounts = accountService.getAllAccounts();

        assertThat(fetchedAccounts).isNotNull();
        assertThat(fetchedAccounts.size()).isEqualTo(2);
        assertThat(fetchedAccounts.get(0).getId()).isEqualTo(1L);
        assertThat(fetchedAccounts.get(1).getId()).isEqualTo(2L);
    }

    @Test
    public void testGetAccountById() {
        Account account = new Account();
        account.setId(1L);

        Mockito.when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));

        Optional<Account> fetchedAccount = accountService.getAccountById(1L);

        assertThat(fetchedAccount).isPresent();
        assertThat(fetchedAccount.get().getId()).isEqualTo(1L);
    }

    @Test
    public void testCreateAccount() {
        Account account = new Account();
        account.setId(1L);

        Mockito.when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account createdAccount = accountService.createAccount(account);

        assertThat(createdAccount).isNotNull();
        assertThat(createdAccount.getId()).isEqualTo(1L);
    }

    @Test
    public void testDeleteAccount() {

        Mockito.doNothing().when(accountRepository).deleteById(anyLong());
        accountService.deleteAccount(1L);
    }

    @Test
    public void testUpdateAccountWhenAccountExists() {
        // Prepare a sample account
        Account existingAccount = new Account();
        existingAccount.setId(1L);
        existingAccount.setAmount(1000L);

        
        Mockito.when(accountRepository.existsById(anyLong())).thenReturn(true);
        Mockito.when(accountRepository.save(any(Account.class))).thenReturn(existingAccount);

        
        Account updatedAccount = accountService.updateAccount(1L, existingAccount);

        
        assertThat(updatedAccount).isNotNull();
        assertThat(updatedAccount.getId()).isEqualTo(existingAccount.getId());
        assertThat(updatedAccount.getAmount()).isEqualTo(existingAccount.getAmount());
    }

    @Test
    public void testUpdateAccountWhenAccountNotExists() {
        
        Account nonExistingAccount = new Account();
        nonExistingAccount.setId(2L);
        nonExistingAccount.setAmount(2000L);

        
        Mockito.when(accountRepository.existsById(anyLong())).thenReturn(false);

        
        Account updatedAccount = accountService.updateAccount(2L, nonExistingAccount);

        
        assertThat(updatedAccount).isNull();
    }

    @Test
    public void testPatchAccountWhenAccountNotPresent() {

        Map<String, Object> updates = new HashMap<>();
        updates.put("type", "Savings");
        updates.put("customerId", 123L);
        updates.put("amount", 1000L);
        updates.put("status", true);

        Mockito.when(accountRepository.findById(anyLong())).thenReturn(Optional.empty());

        Account patchedAccount = accountService.patchAccount(1L, updates);

        assertThat(patchedAccount).isNull();
    }
}
