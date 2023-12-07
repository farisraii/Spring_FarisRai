package com.phincon.bootcamp.faris.model;

import lombok.Data;

@Data

public class AccountDto {
    Long id;
    Long customerId;
    String name;
    String type;
    String status;
    Long amount;
}
