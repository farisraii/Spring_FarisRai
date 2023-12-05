package com.phincon.bootcamp.Bootcamp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AccountTest {

    public static Account account;

    @BeforeAll
    public static void setup(){
        account=new Account();
        account.setId(1);
        account.setName("faris");
        account.setAmount(1.5);
    }

    @Test
    void setId() {
        account.setId(1);
        assertTrue(true);
    }

    @Test
    void setName() {
        account.setName("faris");
        assertTrue(true);
    }

    @Test
    void setAmount() {
        account.setAmount(1.5);
        assertTrue(true);
    }

    @Test
    void getId() {
        assertEquals(1,account.getId());
    }

    @Test
    void getName() {
        assertEquals("faris",account.getName());
    }

    @Test
    void getAmount() {
        assertEquals(1.5, account.getAmount());
    }
}
