package com.epam.spring.homework4.payments.validation;

import com.epam.spring.homework4.payments.validation.impl.OriginalPaymentNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OriginalPaymentNumberValidator.class)
@Target({
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface OriginalPaymentNumber {

    String message() default "Payment with such number already exists!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
