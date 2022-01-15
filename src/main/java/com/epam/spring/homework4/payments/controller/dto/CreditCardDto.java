package com.epam.spring.homework4.payments.controller.dto;

import com.epam.spring.homework4.payments.validation.OriginalCreditCardNumber;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class CreditCardDto {

    private Long creditCardId;

    @NotBlank
    @Positive
    private Long accountId;

    @NotBlank(message = "{credit_card.wrong_cvv}")
    @Min(100)
    @Max(999)
    private Long cvvCode;

    @NotBlank(message = "{credit_card.wrong_pin}")
    @Min(1000)
    @Max(9999)
    private Long pinCode;

    @NotBlank
    @OriginalCreditCardNumber
    @Pattern(regexp = "^\\d{16}$", message = "{credit_card.wrong_number}")
    private String number;

    @NotBlank
    private String paymentSystem;

    @PastOrPresent
    private LocalDate date;

}
