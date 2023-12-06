package com.phincon.bootcamp.faris.model;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountQuizModelTest {

    @Test
    public void testAccountModel() {
        
        Account account = new Account();
        account.setId(1L);
        account.setType("Savings");
        account.setCustomerId(123L);
        account.setAmount(1000L);
        account.setStatus(true);
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());
        account.setCreatedDate(createdDate);

        
        assertThat(account.getId()).isEqualTo(1L);
        assertThat(account.getType()).isEqualTo("Savings");
        assertThat(account.getCustomerId()).isEqualTo(123L);
        assertThat(account.getAmount()).isEqualTo(1000L);
        assertThat(account.getStatus()).isEqualTo(true);
        assertThat(account.getCreatedDate()).isEqualTo(createdDate);

        
        account.setId(2L);
        account.setType("Checking");
        account.setCustomerId(456L);
        account.setAmount(2000L);
        account.setStatus(false);
        Timestamp updatedDate = new Timestamp(System.currentTimeMillis());
        account.setUpdatedDate(updatedDate);

        assertThat(account.getId()).isEqualTo(2L);
        assertThat(account.getType()).isEqualTo("Checking");
        assertThat(account.getCustomerId()).isEqualTo(456L);
        assertThat(account.getAmount()).isEqualTo(2000L);
 
    }
}    