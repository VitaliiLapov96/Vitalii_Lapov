package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validators.MyValidator;

import static com.epam.spring.homework2.constants.ApplicationConstants.MSG_NAME;
import static com.epam.spring.homework2.constants.ApplicationConstants.MSG_VALUE;

public class BeanF implements MyValidator {

    private String name;
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

    @Override
    public void validate() {
        if (name == null)
            System.err.println(this.getClass().getSimpleName() + " " + MSG_NAME);
        if (value < 0)
            System.err.println(this.getClass().getSimpleName() + " " + MSG_VALUE);
    }

    @Override
    public String toString() {
        return "BeanF{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

}
