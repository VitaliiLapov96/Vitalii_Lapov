package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.*;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("com.epam.spring.homework2.beans")
@PropertySource("classpath:application.properties")
public class BeansConfig {

    @Bean
    @DependsOn({"beanD", "beanB", "beanC"})
    public BeanA beanA() {
        return new BeanA();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BeanB beanB() {
        return new BeanB();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BeanC beanC() {
        return new BeanC();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public BeanD beanD() {
        return new BeanD();
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
