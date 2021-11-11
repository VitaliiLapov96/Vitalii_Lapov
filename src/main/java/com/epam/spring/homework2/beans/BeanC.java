package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validators.MyValidator;
import org.springframework.beans.factory.annotation.Value;

import static com.epam.spring.homework2.constants.ApplicationConstants.*;
import static com.epam.spring.homework2.constants.ApplicationConstants.MSG_VALUE;

public class BeanC implements MyValidator {

    @Value("${beanC.name}")
    private String name;
    @Value("${beanC.value}")
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private void init() {
        System.out.println(this.getClass().getSimpleName() + " " + INITIALIZED);
    }

    private void destroy() {
        System.out.println(this.getClass().getSimpleName() + " " + DESTROYED);
    }

    @Override
    public void validate() {
        if (name == null)
            System.err.println(this.getClass().getSimpleName() + " " + MSG_NAME);
        if (value < 0)
            System.err.println(this.getClass().getSimpleName() + " " + MSG_VALUE);
    }

    @Override
    public String toString() {
        return "BeanC{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

}
