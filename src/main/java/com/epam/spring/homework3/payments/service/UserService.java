package com.epam.spring.homework3.payments.service;

import com.epam.spring.homework3.payments.controller.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto getUser(int userId);
    UserDto updateUser(int userId, UserDto userDto);
    void deleteUser(int userId);
    List<UserDto> listUsers();

}
