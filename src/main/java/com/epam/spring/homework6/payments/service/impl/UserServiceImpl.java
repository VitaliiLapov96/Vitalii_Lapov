package com.epam.spring.homework6.payments.service.impl;

import com.epam.spring.homework6.payments.controller.dto.UserDto;
import com.epam.spring.homework6.payments.exception.EntityNotFoundException;
import com.epam.spring.homework6.payments.model.User;
import com.epam.spring.homework6.payments.repository.UserRepository;
import com.epam.spring.homework6.payments.service.UserService;
import com.epam.spring.homework6.payments.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.INSTANCE.mapUserDtoToUser(userDto);
        user = userRepository.save(user);
        log.info("create user with id {}", user.getUserId());
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    @Override
    public UserDto getUser(Long userId) {
        log.info("get user by id {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User", userId));
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto updatedUserDto) {
        log.info("update user by id {}", userId);
        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User", userId));

        updatedUserDto.setUserId(userId);
        User updatedUser = UserMapper.INSTANCE.mapUserDtoToUser(updatedUserDto);
        updatedUser = userRepository.save(updatedUser);
        return UserMapper.INSTANCE.mapUserToUserDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        log.info("delete user by id {}", userId);
        userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User", userId));

        userRepository.deleteById(userId);
    }

    @Override
    public List<UserDto> listUsers(Pageable pageable) {
        log.info("get all users");
        List<User> pageResult = userRepository.findAll(pageable).getContent();
        return UserMapper.INSTANCE.mapUserToUserDtos(pageResult);
    }

}
