package com.epam.spring.homework4.payments.validation.impl;

import com.epam.spring.homework4.payments.service.CreditCardService;
import com.epam.spring.homework4.payments.validation.OriginalCreditCardNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class OriginalCreditCardNumberValidator implements ConstraintValidator<OriginalCreditCardNumber, String> {

    private final CreditCardService creditCardService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return creditCardService.listCreditCards().stream()
                .noneMatch(creditCard -> creditCard.getNumber().equals(value));
    }

}
