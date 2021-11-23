package com.epam.spring.homework3.payments.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class User {

    private int userId;
    private boolean isAdmin;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate date;

}
