package com.epam.spring.homework4.payments.service;

import com.epam.spring.homework4.payments.controller.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccount(Long accountId);
    AccountDto updateAccount(Long accountId, AccountDto updatedAccountDto);
    void deleteAccount(Long accountId);
    List<AccountDto> listAccounts();

}
