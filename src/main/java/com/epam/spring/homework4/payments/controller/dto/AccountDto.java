package com.epam.spring.homework4.payments.controller.dto;

import com.epam.spring.homework4.payments.validation.OriginalAccountNumber;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class AccountDto {

    private Long accountId;

    @NotNull
    @Positive
    private Long userId;

    private Double balance;
    private Double creditLimit;

    @NotBlank
    private String name;

    @NotBlank
    @OriginalAccountNumber
    @Pattern(regexp = "^\\d{14}$", message = "{account.wrong_number}")
    private String number;

    @NotBlank
    @Pattern(regexp = "^[A-Z]{3}$", message = "{wrong_currency}")
    private String currency;

    @PastOrPresent
    private LocalDate date;

}
