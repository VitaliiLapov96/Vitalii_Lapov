package com.epam.spring.homework2.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.epam.spring.homework2.constants.ApplicationConstants.DESTROYED;
import static com.epam.spring.homework2.constants.ApplicationConstants.INITIALIZED;

public class BeanE {

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

    @PostConstruct
    private void init() {
        System.out.println(this.getClass().getSimpleName() + " " + INITIALIZED + " via @PostConstruct");
    }

    @PreDestroy
    private void destroy() {
        System.out.println(this.getClass().getSimpleName() + " " + DESTROYED + " via @PreDestroy");
    }

    @Override
    public String toString() {
        return "BeanE{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

}
