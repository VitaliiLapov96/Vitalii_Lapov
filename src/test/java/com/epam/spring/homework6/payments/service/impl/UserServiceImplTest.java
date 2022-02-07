package com.epam.spring.homework6.payments.service.impl;

import com.epam.spring.homework6.payments.controller.dto.UserDto;
import com.epam.spring.homework6.payments.exception.EntityNotFoundException;
import com.epam.spring.homework6.payments.model.User;
import com.epam.spring.homework6.payments.repository.UserRepository;
import com.epam.spring.homework6.payments.service.mapper.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<User> pageResult;

    @Autowired
    private ObjectMapper mapper;

    private User expectedUser;

    private static final Long MOCK_ID = 1L;

    @BeforeEach
    void setUp() {
        expectedUser = User.builder().userId(MOCK_ID).build();
    }

    @Test
    void shouldCreateUser() {
        //given
        when(userRepository.save(expectedUser)).thenReturn(expectedUser);

        //when
        UserDto actualUser = userService.createUser(UserMapper.INSTANCE.mapUserToUserDto(expectedUser));

        //then
        assertEquals(expectedUser.getUserId(), actualUser.getUserId());
    }

    @Test
    void shouldGetUser() {
        //given
        when(userRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedUser));

        //when
        UserDto actualUser = userService.getUser(MOCK_ID);

        //then
        assertEquals(expectedUser.getUserId(), actualUser.getUserId());
    }

    @Test
    void shouldThrowExceptionWhenReturnUserDoesNotExist() {
        when(userRepository.findById(MOCK_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.getUser(MOCK_ID));
    }

    @Test
    void shouldGetUsers() {
        //given
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(expectedUser);
        when(userRepository.findAll(pageable)).thenReturn(pageResult);
        when(pageResult.getContent()).thenReturn(expectedUsers);

        //when
        List<UserDto> actualUsers = userService.listUsers(pageable);

        //then
        assertThat(actualUsers, hasSize(1));
    }

    @Test
    void shouldUpdateUser() {
        //given
        UserDto expectedUserDto = UserDto.builder().email("test@test.com").build();
        when(userRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedUser));

        //when
        userService.updateUser(MOCK_ID, expectedUserDto);

        //then
        verify(userRepository).save(UserMapper.INSTANCE.mapUserDtoToUser(expectedUserDto));
        verify(userRepository).findById(MOCK_ID);
    }

    @Test
    void shouldThrowExceptionWhenUpdateUserDoesNotExist() {
        UserDto expectedUserDto = UserDto.builder().email("test@test.com").build();

        when(userRepository.findById(MOCK_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.updateUser(MOCK_ID, expectedUserDto));
    }

    @Test
    void shouldDeleteUser() {
        //given
        when(userRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedUser));
        doNothing().when(userRepository).deleteById(MOCK_ID);

        //when
        userService.deleteUser(MOCK_ID);

        //then
        verify(userRepository, times(1)).deleteById(MOCK_ID);
    }

    @Test
    void shouldThrowExceptionWhenDeleteUserDoesNotExist() {
        when(userRepository.findById(MOCK_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> userService.deleteUser(MOCK_ID));
    }

}