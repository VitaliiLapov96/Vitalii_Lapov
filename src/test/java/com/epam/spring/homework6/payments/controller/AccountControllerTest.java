package com.epam.spring.homework6.payments.controller;

import com.epam.spring.homework6.payments.controller.dto.AccountDto;
import com.epam.spring.homework6.payments.controller.dto.UserDto;
import com.epam.spring.homework6.payments.exception.EntityNotFoundException;
import com.epam.spring.homework6.payments.service.AccountService;
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
class AccountControllerTest {

    @MockBean
    private AccountService accountService;

    @Mock
    private Pageable pageable;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private AccountDto account;

    private static final Long MOCK_ID = 1L;
    private static final String ACCOUNT = "Account";

    @BeforeEach
    void setUp() {
        account = AccountDto.builder()
                .accountId(MOCK_ID)
                .user(UserDto.builder().userId(MOCK_ID).build())
                .name("Test")
                .number("11228344556644")
                .currency("USD")
                .build();
    }

    @Test
    void createAccountTest() throws Exception {
        when(accountService.createAccount(account)).thenReturn(account);

        mockMvc.perform(post("/account")
                        .content(mapper.writeValueAsBytes(account))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountId", is(account.getAccountId().intValue())))
                .andExpect(jsonPath("$.name", is(account.getName())))
                .andExpect(jsonPath("$.number", is(account.getNumber())))
                .andExpect(jsonPath("$.currency", is(account.getCurrency())));
    }

    @Test
    void shouldThrowExceptionWhenCreateAccountDoesNotExist() throws Exception {
        doThrow(new EntityNotFoundException(ACCOUNT, MOCK_ID)).when(accountService).createAccount(account);

        mockMvc.perform(post("/account")
                        .content(mapper.writeValueAsBytes(account))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateAccountTest() throws Exception {
        when(accountService.updateAccount(MOCK_ID, account)).thenReturn(account);

        mockMvc.perform(put("/account/{id}", MOCK_ID)
                        .content(mapper.writeValueAsBytes(account))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId", is(account.getAccountId().intValue())))
                .andExpect(jsonPath("$.name", is(account.getName())))
                .andExpect(jsonPath("$.number", is(account.getNumber())))
                .andExpect(jsonPath("$.currency", is(account.getCurrency())));
    }

    @Test
    void shouldThrowExceptionWhenUpdateAccountDoesNotExist() throws Exception {
        doThrow(new EntityNotFoundException(ACCOUNT, MOCK_ID)).when(accountService).updateAccount(MOCK_ID, account);

        mockMvc.perform(put("/account/{id}", MOCK_ID)
                        .content(mapper.writeValueAsBytes(account))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAccountTest() throws Exception {
        when(accountService.getAccount(MOCK_ID)).thenReturn(account);

        mockMvc.perform(get("/account/{id}", MOCK_ID)
                .content(mapper.writeValueAsBytes(account))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId", is(account.getAccountId().intValue())))
                .andExpect(jsonPath("$.name", is(account.getName())))
                .andExpect(jsonPath("$.number", is(account.getNumber())))
                .andExpect(jsonPath("$.currency", is(account.getCurrency())));
    }

    @Test
    void shouldThrowExceptionWhenGetAccountDoesNotExist() throws Exception {
        doThrow(new EntityNotFoundException(ACCOUNT, MOCK_ID)).when(accountService).getAccount(MOCK_ID);

        mockMvc.perform(get("/account/{id}", MOCK_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getListAccountsTest() throws Exception {
        List<AccountDto> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(account);

        when(accountService.listAccounts(pageable)).thenReturn(expectedAccounts);

        mockMvc.perform(get("/account")
                        .content(mapper.writeValueAsBytes(expectedAccounts))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAccountTest() throws Exception {
        doNothing().when(accountService).deleteAccount(MOCK_ID);

        mockMvc.perform(delete("/account/{id}", MOCK_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldThrowExceptionWhenDeleteAccountDoesNotExist() throws Exception {
        doThrow(new EntityNotFoundException(ACCOUNT, MOCK_ID)).when(accountService).deleteAccount(MOCK_ID);

        mockMvc.perform(delete("/account/{id}", MOCK_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}