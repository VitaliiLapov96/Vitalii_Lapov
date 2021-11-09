package com.epam.spring.homework1.pet;

import org.springframework.stereotype.Component;

@Component
public class Dog implements Animal {

    public String getAnimal() {
        return "Dog";
    }

}
