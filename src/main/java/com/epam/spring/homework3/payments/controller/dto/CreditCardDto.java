package com.epam.spring.homework3.payments.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CreditCardDto {

    private Long creditCardId;
    private Long accountId;
    private Long cvvCode;
    private Long pinCode;
    private String number;
    private String paymentSystem;
    private LocalDate date;

}
