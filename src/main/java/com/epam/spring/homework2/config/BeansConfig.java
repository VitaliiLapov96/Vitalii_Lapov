package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@ComponentScan("com.epam.spring.homework2.beans")
public class BeansConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BeanD beanD() {
        return new BeanD();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BeanB beanB() {
        return new BeanB();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BeanC beanC() {
        return new BeanC();
    }

    @Bean
    public BeanA beanA() {
        return new BeanA();
    }

    @Bean
    public BeanE beanE() {
        return new BeanE();
    }

    @Bean
    @Lazy
    public BeanF beanF() {
        return new BeanF();
    }

}
