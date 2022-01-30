package com.epam.spring.homework5.payments.controller.dto;

import com.epam.spring.homework5.payments.validation.SamePasswords;
import lombok.Builder;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

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
    @Email
    private String email;

    @NotBlank
    private String password;

    @JsonIgnore
    @NotBlank
    private String repeatPassword;

    @PastOrPresent
    private LocalDate date;

}
