package com.epam.spring.homework4.payments.service.impl;

import com.epam.spring.homework4.payments.controller.dto.UserDto;
import com.epam.spring.homework4.payments.model.User;
import com.epam.spring.homework4.payments.repository.UserRepository;
import com.epam.spring.homework4.payments.service.UserService;
import com.epam.spring.homework4.payments.service.mapper.UserMapper;
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
        User user = UserMapper.INSTANCE.mapUserDtoToUser(userDto);
        user = userRepository.createUser(user);
        log.info("create user with id {}", user.getUserId());
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    @Override
    public UserDto getUser(Long userId) {
        log.info("get user by id {}", userId);
        User user = userRepository.getUser(userId);
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto updatedUserDto) {
        log.info("update user by id {}", userId);
        User updatedUser = UserMapper.INSTANCE.mapUserDtoToUser(updatedUserDto);
        updatedUser = userRepository.updateUser(userId, updatedUser);
        return UserMapper.INSTANCE.mapUserToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("delete user by id {}", userId);
        userRepository.deleteUser(userId);
    }

    @Override
    public List<UserDto> listUsers() {
        log.info("get all users");
        return userRepository.listUsers().stream()
                .map(UserMapper.INSTANCE::mapUserToUserDto)
                .collect(Collectors.toList());
    }

}
