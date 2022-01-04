package com.epam.spring.homework3.payments.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PaymentDto {

    private int paymentId;
    private int accountIdFrom;
    private int accountIdTo;
    private double amount;
    private String number;
    private String description;
    private String currency;
    private LocalDate date;

}
