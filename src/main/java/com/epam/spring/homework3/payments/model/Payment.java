package com.epam.spring.homework3.payments.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Payment {

    private int paymentId;
    private int accountIdFrom;
    private int accountIdTo;
    private String number;
    private String description;
    private String currency;
    private LocalDate date;

}
