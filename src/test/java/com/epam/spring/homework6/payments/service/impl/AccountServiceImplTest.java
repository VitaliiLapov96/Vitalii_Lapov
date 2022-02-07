package com.epam.spring.homework6.payments.service.impl;

import com.epam.spring.homework6.payments.controller.dto.AccountDto;
import com.epam.spring.homework6.payments.exception.EntityNotFoundException;
import com.epam.spring.homework6.payments.model.Account;
import com.epam.spring.homework6.payments.repository.AccountRepository;
import com.epam.spring.homework6.payments.service.mapper.AccountMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<Account> pageResult;

    private Account expectedAccount;

    private static final Long MOCK_ID = 1L;

    @BeforeEach
    void setUp() {
        expectedAccount = Account.builder().accountId(MOCK_ID).build();
    }

    @Test
    void shouldCreateAccount() {
        //given
        when(accountRepository.save(expectedAccount)).thenReturn(expectedAccount);

        //when
        AccountDto actualAccount = accountService
                .createAccount(AccountMapper.INSTANCE.mapAccountToAccountDto(expectedAccount));

        //then
        assertEquals(expectedAccount.getAccountId(), actualAccount.getAccountId());
    }

    @Test
    void shouldReturnAccount() {
        //given
        when(accountRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedAccount));

        //when
        AccountDto actualAccount = accountService.getAccount(MOCK_ID);

        //then
        assertEquals(expectedAccount.getAccountId(), actualAccount.getAccountId());
    }

    @Test
    void shouldThrowExceptionWhenReturnAccountDoesNotExist() {
        when(accountRepository.findById(MOCK_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> accountService.getAccount(MOCK_ID));
    }

    @Test
    void shouldReturnAllAccounts() {
        //given
        List<Account> expectedAccounts = new ArrayList<>();
        expectedAccounts.add(expectedAccount);
        when(accountRepository.findAll(pageable)).thenReturn(pageResult);
        when(pageResult.getContent()).thenReturn(expectedAccounts);

        //when
        List<AccountDto> actualAccounts = accountService.listAccounts(pageable);

        //then
        assertThat(actualAccounts, hasSize(1));
    }

    @Test
    void shouldUpdateAccount() {
        //given
        AccountDto expectedAccountDto = AccountDto.builder().number("777777777777777").build();
        when(accountRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedAccount));

        //when
        accountService.updateAccount(MOCK_ID, expectedAccountDto);

        //then
        verify(accountRepository).save(AccountMapper.INSTANCE.mapAccountDtoToAccount(expectedAccountDto));
        verify(accountRepository).findById(MOCK_ID);
    }

    @Test
    void shouldThrowExceptionWhenUpdateAccountDoesNotExist() {
        AccountDto expectedAccountDto = AccountDto.builder().number("777777777777777").build();

        when(accountRepository.findById(MOCK_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> accountService.updateAccount(MOCK_ID, expectedAccountDto));
    }

    @Test
    void shouldDeleteAccount() {
        //given
        when(accountRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedAccount));
        doNothing().when(accountRepository).deleteById(MOCK_ID);

        //when
        accountService.deleteAccount(MOCK_ID);

        //then
        verify(accountRepository, times(1)).deleteById(MOCK_ID);
    }

    @Test
    void shouldThrowExceptionWhenDeleteAccountDoesNotExist() {
        when(accountRepository.findById(MOCK_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> accountService.deleteAccount(MOCK_ID));
    }

}