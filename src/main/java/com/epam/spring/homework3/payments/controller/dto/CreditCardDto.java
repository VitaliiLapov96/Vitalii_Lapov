package com.epam.spring.homework3.payments.controller.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class CreditCardDto {

    private Long creditCardId;

    @Positive
    private Long accountId;

    @NotEmpty(message = "{creditCard.wrongCvv}")
    @Min(100)
    @Max(999)
    private Long cvvCode;

    @NotEmpty(message = "{creditCard.wrongPin}")
    @Min(1000)
    @Max(9999)
    private Long pinCode;

    @NotEmpty(message = "{creditCard.wrongNumber}")
    @Pattern(regexp = "^\\d{16}$")
    @NotBlank
    private String number;

    @NotBlank
    private String paymentSystem;

    @PastOrPresent
    private LocalDate date;

}
