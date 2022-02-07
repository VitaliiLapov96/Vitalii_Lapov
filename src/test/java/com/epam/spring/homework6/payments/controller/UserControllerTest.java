package com.epam.spring.homework6.payments.controller;

import com.epam.spring.homework6.payments.controller.dto.UserDto;
import com.epam.spring.homework6.payments.exception.EntityNotFoundException;
import com.epam.spring.homework6.payments.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService userService;

    @Mock
    private Pageable pageable;

    private UserDto user;

    private static final Long MOCK_ID = 1L;
    private static final String USER = "User";

    @BeforeEach
    void setUp() {
        user = UserDto.builder()
                .userId(MOCK_ID)
                .isAdmin(false)
                .firstName("Test")
                .lastName("Testenko")
                .email("test@test.com")
                .password("test")
                .repeatPassword("test")
                .build();
    }

    @Test
    void createUserTest() throws Exception {
        when(userService.createUser(user)).thenReturn(user);

        mockMvc.perform(post("/user")
                        .content(mapper.writeValueAsBytes(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId", is(user.getUserId().intValue())))
                .andExpect(jsonPath("$.isAdmin", is(user.getIsAdmin())))
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.password", is(user.getPassword())));
    }

    @Test
    void shouldThrowExceptionWhenCreateUserDoesNotExist() throws Exception {
        doThrow(new EntityNotFoundException(USER, MOCK_ID)).when(userService).createUser(user);

        mockMvc.perform(post("/user")
                        .content(mapper.writeValueAsBytes(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateUserTest() throws Exception {
        when(userService.updateUser(MOCK_ID, user)).thenReturn(user);

        mockMvc.perform(put("/user/{id}", MOCK_ID)
                        .content(mapper.writeValueAsBytes(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(user.getUserId().intValue())))
                .andExpect(jsonPath("$.isAdmin", is(user.getIsAdmin())))
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.password", is(user.getPassword())));    }

    @Test
    void shouldThrowExceptionWhenUpdateUserDoesNotExist() throws Exception {
        doThrow(new EntityNotFoundException(USER, MOCK_ID)).when(userService).updateUser(MOCK_ID, user);

        mockMvc.perform(put("/user/{id}", MOCK_ID)
                        .content(mapper.writeValueAsBytes(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getUserTest() throws Exception {
        when(userService.getUser(MOCK_ID)).thenReturn(user);

        mockMvc.perform(get("/user/{id}", MOCK_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId", is(user.getUserId().intValue())))
                .andExpect(jsonPath("$.isAdmin", is(user.getIsAdmin())))
                .andExpect(jsonPath("$.firstName", is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(user.getLastName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())))
                .andExpect(jsonPath("$.password", is(user.getPassword())));    }

    @Test
    void shouldThrowExceptionWhenGetUserDoesNotExist() throws Exception {
        doThrow(new EntityNotFoundException(USER, MOCK_ID)).when(userService).getUser(MOCK_ID);

        mockMvc.perform(get("/user/{id}", MOCK_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllUsersTest() throws Exception {
        List<UserDto> expectedUsers = new ArrayList<>();
        expectedUsers.add(user);

        when(userService.listUsers(pageable)).thenReturn(expectedUsers);

        mockMvc.perform(get("/user")
                        .content(mapper.writeValueAsBytes(expectedUsers))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUserTest() throws Exception {
        doNothing().when(userService).deleteUser(MOCK_ID);

        mockMvc.perform(delete("/user/{id}", MOCK_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldThrowExceptionWhenDeleteUserDoesNotExist() throws Exception {
        doThrow(new EntityNotFoundException(USER, MOCK_ID)).when(userService).deleteUser(MOCK_ID);

        mockMvc.perform(delete("/user/{id}", MOCK_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}