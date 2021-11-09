package com.epam.spring.homework1.config;

import com.epam.spring.homework1.pet.Spider;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"com.epam.spring.homework1.pet"},
        excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE, value = Spider.class))
public class PetConfig {

}
