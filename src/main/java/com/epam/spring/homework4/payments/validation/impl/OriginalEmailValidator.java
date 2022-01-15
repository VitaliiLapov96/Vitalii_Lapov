package com.epam.spring.homework4.payments.validation.impl;

import com.epam.spring.homework4.payments.service.UserService;
import com.epam.spring.homework4.payments.validation.OriginalEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class OriginalEmailValidator implements ConstraintValidator<OriginalEmail, String> {

    private final UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return userService.listUsers().stream()
                .noneMatch(user -> user.getEmail().equals(value));
    }

}
