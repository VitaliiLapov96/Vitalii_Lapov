package com.epam.spring.homework3.payments.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class AccountDto {

    private Long accountId;

    @Positive
    private Long userId;

    private Double balance;
    private Double creditLimit;

    @NotBlank
    private String name;

    @NotEmpty(message = "{account.wrongNumber}")
    @Pattern(regexp = "^\\d{14}$")
    @NotBlank
    private String number;

    @NotEmpty(message = "{account.wrongCurrency}")
    @Pattern(regexp = "^[A-Z]{3}$")
    @NotBlank
    private String currency;

    @PastOrPresent
    private LocalDate date;

}
