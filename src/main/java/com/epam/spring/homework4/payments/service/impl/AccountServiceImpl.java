package com.epam.spring.homework4.payments.service.impl;

import com.epam.spring.homework4.payments.controller.dto.AccountDto;
import com.epam.spring.homework4.payments.model.Account;
import com.epam.spring.homework4.payments.repository.AccountRepository;
import com.epam.spring.homework4.payments.service.AccountService;
import com.epam.spring.homework4.payments.service.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.INSTANCE.mapAccountDtoToAccount(accountDto);
        account = accountRepository.createAccount(account);
        log.info("create account with number {}", account.getNumber());
        return AccountMapper.INSTANCE.mapAccountToAccountDto(account);
    }

    @Override
    public AccountDto getAccount(Long accountId) {
        log.info("get account by id {}", accountId);
        Account account = accountRepository.getAccount(accountId);
        return AccountMapper.INSTANCE.mapAccountToAccountDto(account);
    }

    @Override
    public AccountDto updateAccount(Long accountId, AccountDto updatedAccountDto) {
        log.info("update account by id {}", accountId);
        Account updatedAccount = AccountMapper.INSTANCE.mapAccountDtoToAccount(updatedAccountDto);
        updatedAccount = accountRepository.updateAccount(accountId, updatedAccount);
        return AccountMapper.INSTANCE.mapAccountToAccountDto(updatedAccount);
    }

    @Override
    public void deleteAccount(Long accountId) {
        log.info("delete account by id {}", accountId);
        accountRepository.deleteAccount(accountId);
    }

    @Override
    public List<AccountDto> listAccounts() {
        log.info("get all accounts");
        return accountRepository.listAccounts().stream()
                .map(AccountMapper.INSTANCE::mapAccountToAccountDto)
                .collect(Collectors.toList());
    }

}
