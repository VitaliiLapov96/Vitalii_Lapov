package com.epam.spring.homework3.payments.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PaymentDto {

    private Long paymentId;
    private Long accountIdFrom;
    private Long accountIdTo;
    private Double amount;
    private String number;
    private String description;
    private String currency;
    private LocalDate date;


}
