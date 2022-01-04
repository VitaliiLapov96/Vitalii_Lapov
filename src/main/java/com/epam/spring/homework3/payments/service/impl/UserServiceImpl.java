package com.epam.spring.homework3.payments.service.impl;

import com.epam.spring.homework3.payments.controller.dto.UserDto;
import com.epam.spring.homework3.payments.model.User;
import com.epam.spring.homework3.payments.repository.UserRepository;
import com.epam.spring.homework3.payments.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapUserDtoToUser(userDto);
        user = userRepository.createUser(user);
        log.info("create user with id {}", user.getUserId());
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto getUser(int userId) {
        log.info("get user by id {}", userId);
        User user = userRepository.getUser(userId);
        return mapUserToUserDto(user);
    }

    @Override
    public UserDto updateUser(int userId, UserDto updatedUserDto) {
        log.info("update user by id {}", userId);
        User updatedUser = mapUserDtoToUser(updatedUserDto);
        updatedUser = userRepository.updateUser(userId, updatedUser);
        return mapUserToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(int userId) {
        log.info("delete user by id {}", userId);
        userRepository.deleteUser(userId);
    }


    @Override
    public List<UserDto> listUsers() {
        log.info("get all users");
        return userRepository.listUsers().stream()
                .map(this::mapUserToUserDto)
                .collect(Collectors.toList());
    }

    private UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .isAdmin(user.isAdmin())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .date(user.getDate())
                .build();
    }

    private User mapUserDtoToUser(UserDto userDto) {
        return User.builder()
                .userId(userDto.getUserId())
                .isAdmin(userDto.isAdmin())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .date(userDto.getDate())
                .build();
    }

}
