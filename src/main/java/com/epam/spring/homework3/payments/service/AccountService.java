package com.epam.spring.homework3.payments.service;

import com.epam.spring.homework3.payments.controller.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccount(int accountId);
    AccountDto updateAccount(int accountId, AccountDto updatedAccountDto);
    void deleteAccount(int accountId);
    List<AccountDto> listAccounts();

}
