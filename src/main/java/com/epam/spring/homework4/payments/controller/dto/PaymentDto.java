package com.epam.spring.homework4.payments.controller.dto;

import com.epam.spring.homework4.payments.validation.OriginalPaymentNumber;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class PaymentDto {

    private Long paymentId;

    @NotNull
    @Positive
    private Long accountIdFrom;

    @NotNull
    @Positive
    private Long accountIdTo;

    private Double amount;

    @NotBlank
    @OriginalPaymentNumber
    @Pattern(regexp = "^\\d+$", message = "{payment.wrong_number}")
    private String number;

    private String description;

    @NotBlank
    @Pattern(regexp = "^[A-Z]{3}$", message = "{wrong_currency}")
    private String currency;

    @PastOrPresent
    private LocalDate date;

}
