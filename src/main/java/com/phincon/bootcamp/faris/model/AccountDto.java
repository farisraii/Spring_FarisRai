package com.phincon.bootcamp.faris.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class AccountDto {
    @NotBlank(message = "id mandatory")
    String id;

    String customerId;
    String name;
    String type;
    String status;
    Long amount;
}
