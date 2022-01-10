package com.epam.spring.homework3.payments.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class PaymentDto {

    private Long paymentId;

    @Positive
    private Long accountIdFrom;

    @Positive
    private Long accountIdTo;

    private Double amount;

    @NotEmpty(message = "{payment.wrongNumber}")
    @Pattern(regexp = "^\\d+$")
    @NotBlank
    private String number;

    private String description;

    @NotEmpty(message = "{payment.wrongCurrency}")
    @Pattern(regexp = "^[A-Z]{3}$")
    @NotBlank
    private String currency;

    @PastOrPresent
    private LocalDate date;

}
