package com.epam.spring.homework3.payments.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CreditCardDto {

    private int creditCardId;
    private int accountId;
    private int cvvCode;
    private int pinCode;
    private String number;
    private String paymentSystem;
    private LocalDate date;

}
