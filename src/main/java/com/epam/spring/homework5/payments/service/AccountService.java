package com.epam.spring.homework5.payments.service;

import com.epam.spring.homework5.payments.controller.dto.AccountDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccount(Long accountId);
    AccountDto updateAccount(Long accountId, AccountDto updatedAccountDto);
    void deleteAccount(Long accountId);
    List<AccountDto> listAccounts(Pageable pageable);

}
