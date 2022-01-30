package com.epam.spring.homework5.payments.validation.impl;

import com.epam.spring.homework5.payments.controller.dto.UserDto;
import com.epam.spring.homework5.payments.validation.SamePasswords;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class SamePasswordValidator implements ConstraintValidator<SamePasswords, UserDto> {

    @Override
    public boolean isValid(final UserDto user, ConstraintValidatorContext context) {
        String password = user.getPassword();
        String repeatPassword = user.getRepeatPassword();

        return password.equals(repeatPassword);
    }

}
