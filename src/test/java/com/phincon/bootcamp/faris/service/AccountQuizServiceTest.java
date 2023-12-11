package com.phincon.bootcamp.faris.service;

import com.phincon.bootcamp.faris.model.Account;
import com.phincon.bootcamp.faris.repository.AccountQuizRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

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
        Account existingAccount = createSampleAccount(1L, "Savings", 123L, 1000L, true);

        Mockito.when(accountRepository.existsById(anyLong())).thenReturn(true);
        Mockito.when(accountRepository.save(any(Account.class))).thenReturn(existingAccount);

        Account updatedAccount = accountService.updateAccount(1L, existingAccount);

        assertAccountFields(updatedAccount, 1L, "Savings", 123L, 1000L, true);
    }

    @Test
    public void testPatchAccount() {
        Account existingAccount = createSampleAccount(1L, "Savings", 123L, 1000L, true);

        Map<String, Object> updates = new HashMap<>();
        updates.put("type", "Checking");
        updates.put("customerId", 456L);
        updates.put("amount", 2000L);
        updates.put("status", false);

        Mockito.when(accountRepository.findById(anyLong())).thenReturn(Optional.of(existingAccount));
        Mockito.when(accountRepository.save(any(Account.class))).thenReturn(existingAccount);

        Account patchedAccount = accountService.patchAccount(1L, updates);

        assertAccountFields(patchedAccount, 1L, "Checking", 456L, 2000L, false);
    }

    @Test
    public void testGetAllAccounts() {
        Account account1 = createSampleAccount(1L, "Savings", 123L, 1000L, true);
        Account account2 = createSampleAccount(2L, "Checking", 456L, 2000L, false);

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
        Account account = createSampleAccount(1L, "Savings", 123L, 1000L, true);

        Mockito.when(accountRepository.findById(anyLong())).thenReturn(Optional.of(account));

        Optional<Account> fetchedAccount = accountService.getAccountById(1L);

        assertThat(fetchedAccount).isPresent();
        assertThat(fetchedAccount.get().getId()).isEqualTo(1L);
    }

    @Test
    public void testCreateAccount() {
        Account account = createSampleAccount(1L, "Savings", 123L, 1000L, true);

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
        Account existingAccount = createSampleAccount(1L, "Savings", 123L, 1000L, true);

        Mockito.when(accountRepository.existsById(anyLong())).thenReturn(true);
        Mockito.when(accountRepository.save(any(Account.class))).thenReturn(existingAccount);

        Account updatedAccount = accountService.updateAccount(1L, existingAccount);

        assertThat(updatedAccount).isNotNull();
        assertThat(updatedAccount.getId()).isEqualTo(existingAccount.getId());
        assertThat(updatedAccount.getAmount()).isEqualTo(existingAccount.getAmount());
    }

    @Test
    public void testUpdateAccountWhenAccountNotExists() {
        Account nonExistingAccount = createSampleAccount(2L, "Savings", 456L, 2000L, true);

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

    private Account createSampleAccount(Long id, String type, Long customerId, Long amount, boolean status) {
        Account account = new Account();
        account.setId(id);
        account.setType(type);
        account.setCustomerId(customerId);
        account.setAmount(amount);
        account.setStatus(status);
        return account;
    }

    private void assertAccountFields(Account account, Long id, String type, Long customerId, Long amount, boolean status) {
        assertThat(account).isNotNull();
        assertThat(account.getId()).isEqualTo(id);
        assertThat(account.getType()).isEqualTo(type);
        assertThat(account.getCustomerId()).isEqualTo(customerId);
        assertThat(account.getAmount()).isEqualTo(amount);
        assertThat(account.getStatus()).isEqualTo(status);
    }
}
