package com.epam.spring.homework4.payments.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Payment {

    private Long paymentId;
    private Long accountIdFrom;
    private Long accountIdTo;
    private Double amount;
    private String number;
    private String description;
    private String currency;
    private LocalDate date;

}
