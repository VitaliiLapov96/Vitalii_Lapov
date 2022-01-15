package com.epam.spring.homework4.payments.validation;

import com.epam.spring.homework4.payments.validation.impl.OriginalEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OriginalEmailValidator.class)
@Target({
        ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
public @interface OriginalEmail {

    String message() default "User with such email already exists!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
