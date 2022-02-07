package com.epam.spring.homework6.payments.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Builder
public class AccountDto {

    private Long accountId;

    @NotNull
    private UserDto user;

    private Double balance;
    private Double creditLimit;

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "^\\d{14}$", message = "{account.wrong_number}")
    private String number;

    @NotBlank
    @Pattern(regexp = "^[A-Z]{3}$", message = "{wrong_currency}")
    private String currency;

    @PastOrPresent
    private LocalDate date;

}
