package com.epam.spring.homework4.payments.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class User {

    private Long userId;
    private Boolean isAdmin;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String repeatPassword;
    private LocalDate date;

}
