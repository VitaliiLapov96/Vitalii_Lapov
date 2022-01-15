package com.epam.spring.homework4.payments.validation;

import com.epam.spring.homework4.payments.validation.impl.OriginalCreditCardNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OriginalCreditCardNumberValidator.class)
@Target({
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface OriginalCreditCardNumber {

    String message() default "Credit card with such number already exists!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
