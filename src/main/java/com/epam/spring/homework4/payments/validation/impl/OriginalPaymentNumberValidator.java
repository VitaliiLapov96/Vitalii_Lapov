package com.epam.spring.homework4.payments.validation.impl;

import com.epam.spring.homework4.payments.service.PaymentService;
import com.epam.spring.homework4.payments.validation.OriginalPaymentNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class OriginalPaymentNumberValidator implements ConstraintValidator<OriginalPaymentNumber, String> {

    private final PaymentService paymentService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return paymentService.listPayments().stream()
                .noneMatch(payment -> payment.getNumber().equals(value));
    }

}
