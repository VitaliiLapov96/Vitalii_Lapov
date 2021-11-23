package com.epam.spring.homework3.payments.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CreditCard {

    private int creditCardId;
    private int accountId;
    private int cvvCode;
    private int pinCode;
    private String number;
    private String paymentSystem;
    private LocalDate date;

}
