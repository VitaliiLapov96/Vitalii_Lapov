package com.epam.spring.homework2;

import com.epam.spring.homework2.beans.*;
import com.epam.spring.homework2.config.ApplicationConfig;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        System.out.println(context.getBean(BeanA.class));
        System.out.println(context.getBean(BeanB.class));
        System.out.println(context.getBean(BeanC.class));
        System.out.println(context.getBean(BeanD.class));
        System.out.println(context.getBean(BeanE.class));
        context.close();

    }

}
