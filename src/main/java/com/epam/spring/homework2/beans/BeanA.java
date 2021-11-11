package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validators.MyValidator;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import static com.epam.spring.homework2.constants.ApplicationConstants.*;

public class BeanA implements InitializingBean, DisposableBean, MyValidator {

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
        System.out.println(this.getClass().getSimpleName() + " call destroy()");

    }

    @Override
    public void afterPropertiesSet() {
        System.out.println(this.getClass().getSimpleName() + " call afterPropertiesSet()");
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
        return "BeanA{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

}
