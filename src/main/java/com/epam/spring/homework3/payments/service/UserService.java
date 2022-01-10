package com.epam.spring.homework3.payments.service;

import com.epam.spring.homework3.payments.controller.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto getUser(Long userId);
    UserDto updateUser(Long userId, UserDto userDto);
    void deleteUser(Long userId);
    List<UserDto> listUsers();

}
