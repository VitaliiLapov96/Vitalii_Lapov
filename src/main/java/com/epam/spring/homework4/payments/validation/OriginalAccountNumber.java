package com.epam.spring.homework4.payments.validation;

import com.epam.spring.homework4.payments.validation.impl.OriginalAccountNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OriginalAccountNumberValidator.class)
@Target({
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface OriginalAccountNumber {

    String message() default "Account with such number already exists!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
