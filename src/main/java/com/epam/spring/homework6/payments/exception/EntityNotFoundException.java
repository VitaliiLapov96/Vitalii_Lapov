package com.epam.spring.homework6.payments.exception;

import static java.lang.String.format;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entityName, Long id) {
        super(format("%s with id %s is not found", entityName, id));
    }

}
