package com.epam.spring.homework3.payments.service.impl;

import com.epam.spring.homework3.payments.controller.dto.AccountDto;
import com.epam.spring.homework3.payments.model.Account;
import com.epam.spring.homework3.payments.repository.AccountRepository;
import com.epam.spring.homework3.payments.service.AccountService;
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
        log.info("create account with number {}", accountDto.getNumber());
        Account account = mapAccountDtoToAccount(accountDto);
        account = accountRepository.createAccount(account);
        return mapAccountToAccountDto(account);
    }

    @Override
    public AccountDto getAccount(int accountId) {
        log.info("get account by id {}", accountId);
        Account account = accountRepository.getAccount(accountId);
        return mapAccountToAccountDto(account);
    }

    @Override
    public AccountDto updateAccount(int accountId, AccountDto updatedAccountDto) {
        log.info("update account by id {}", accountId);
        Account updatedAccount = mapAccountDtoToAccount(updatedAccountDto);
        updatedAccount = accountRepository.updateAccount(accountId, updatedAccount);
        return mapAccountToAccountDto(updatedAccount);
    }

    @Override
    public void deleteAccount(int accountId) {
        log.info("delete account by id {}", accountId);
        accountRepository.deleteAccount(accountId);
    }

    @Override
    public List<AccountDto> listAccounts() {
        log.info("get all accounts");
        return accountRepository.listAccounts().stream()
                .map(this::mapAccountToAccountDto)
                .collect(Collectors.toList());
    }

    private AccountDto mapAccountToAccountDto(Account account) {
        return AccountDto.builder()
                .accountId(account.getAccountId())
                .userId(account.getUserId())
                .balance(account.getBalance())
                .creditLimit(account.getCreditLimit())
                .name(account.getName())
                .number(account.getNumber())
                .currency(account.getCurrency())
                .date(account.getDate())
                .build();
    }

    private Account mapAccountDtoToAccount(AccountDto accountDto) {
        return Account.builder()
                .accountId(accountDto.getAccountId())
                .userId(accountDto.getUserId())
                .balance(accountDto.getBalance())
                .creditLimit(accountDto.getCreditLimit())
                .name(accountDto.getName())
                .number(accountDto.getNumber())
                .currency(accountDto.getCurrency())
                .date(accountDto.getDate())
                .build();
    }

}
