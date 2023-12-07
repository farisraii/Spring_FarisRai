package com.phincon.bootcamp.faris.model;

import java.security.Timestamp;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table(name = "Customers")
@Entity


public class Customer {

    @Id

    private String id;
    private String name;
    private String status;
    private LocalDate birthDate;
    private Timestamp createdDate;
    private Timestamp updatedDate;

    
}
