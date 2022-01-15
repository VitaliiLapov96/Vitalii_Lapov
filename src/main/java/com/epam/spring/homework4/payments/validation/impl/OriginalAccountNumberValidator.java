package com.epam.spring.homework4.payments.validation.impl;

import com.epam.spring.homework4.payments.service.AccountService;
import com.epam.spring.homework4.payments.validation.OriginalAccountNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class OriginalAccountNumberValidator implements ConstraintValidator<OriginalAccountNumber, String> {

    private final AccountService accountService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return accountService.listAccounts().stream()
                .noneMatch(account -> account.getNumber().equals(value));
    }

}
