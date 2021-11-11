package com.epam.spring.homework2.beans;

import static com.epam.spring.homework2.constants.ApplicationConstants.DESTROYED;
import static com.epam.spring.homework2.constants.ApplicationConstants.INITIALIZED;

public class BeanB {

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

    private void init() {
        System.out.println(this.getClass().getSimpleName() + " " + INITIALIZED);
    }

    private void destroy() {
        System.out.println(this.getClass().getSimpleName() + " " + DESTROYED);
    }

    @Override
    public String toString() {
        return "BeanB{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

}
