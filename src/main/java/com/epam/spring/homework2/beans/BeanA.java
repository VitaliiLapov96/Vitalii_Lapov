package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import static com.epam.spring.homework2.constants.ApplicationConstants.DESTROYED;

public class BeanA implements InitializingBean, DisposableBean {

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
    public void destroy() {
        System.out.println(this.getClass().getSimpleName() + " " + DESTROYED);

    }

    @Override
    public void afterPropertiesSet() {
        System.out.println(this.getClass().getSimpleName() + " " + "after properties set");
    }

    @Override
    public String toString() {
        return "BeanA{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

}
