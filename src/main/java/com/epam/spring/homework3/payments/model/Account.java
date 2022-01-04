package com.epam.spring.homework3.payments.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Account {

    private int accountId;
    private int userId;
    private double creditLimit;
    private double balance;
    private String name;
    private String number;
    private String currency;
    private LocalDate date;

}
