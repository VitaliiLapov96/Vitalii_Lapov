package com.epam.spring.homework6.payments.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class CreditCardDto {

    private Long creditCardId;

    @NotNull
    private AccountDto account;

    @NotNull(message = "{credit_card.wrong_cvv}")
    @Min(100)
    @Max(999)
    private Long cvvCode;

    @NotNull(message = "{credit_card.wrong_pin}")
    @Min(1000)
    @Max(9999)
    private Long pinCode;

    @NotBlank
    @Pattern(regexp = "^\\d{16}$", message = "{credit_card.wrong_number}")
    private String number;

    @NotBlank
    private String paymentSystem;

    @PastOrPresent
    private LocalDate date;

}
