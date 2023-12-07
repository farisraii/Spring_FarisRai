package com.phincon.bootcamp.faris.model;

import java.time.LocalDate;

import lombok.Data;


@Data

public class CustomerDto {
    private String id;
    private String name;
    private String status;
    private LocalDate birthDate;
}
