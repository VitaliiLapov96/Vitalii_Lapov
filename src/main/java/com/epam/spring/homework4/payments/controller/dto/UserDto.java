package com.epam.spring.homework4.payments.controller.dto;

import com.epam.spring.homework4.payments.validation.OriginalEmail;
import com.epam.spring.homework4.payments.validation.SamePasswords;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Data
@Builder
@SamePasswords
public class UserDto {

    private Long userId;

    private Boolean isAdmin;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @OriginalEmail
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String repeatPassword;

    @PastOrPresent
    private LocalDate date;

}
