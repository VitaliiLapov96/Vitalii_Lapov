package com.epam.spring.homework5.payments.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@Builder
public class PaymentDto {

    private Long paymentId;

    @NotNull
    private AccountDto accountFrom;

    @NotNull
    private AccountDto accountTo;

    private Double amount;

    @NotBlank
    @Pattern(regexp = "^\\d+$", message = "{payment.wrong_number}")
    private String number;

    private String description;

    @NotBlank
    @Pattern(regexp = "^[A-Z]{3}$", message = "{wrong_currency}")
    private String currency;

    @PastOrPresent
    private LocalDateTime date;

}
