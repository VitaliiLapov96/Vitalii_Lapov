package com.epam.spring.homework6.payments.validation;

import com.epam.spring.homework6.payments.validation.impl.SamePasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SamePasswordValidator.class)
@Target({
        ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface SamePasswords {

    String message() default "‘password’ and ‘repeatPassword’ are not the same!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
