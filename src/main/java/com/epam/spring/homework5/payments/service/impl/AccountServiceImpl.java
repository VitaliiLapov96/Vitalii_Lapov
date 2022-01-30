package com.epam.spring.homework5.payments.service.impl;

import com.epam.spring.homework5.payments.controller.dto.AccountDto;
import com.epam.spring.homework5.payments.exception.EntityNotFoundException;
import com.epam.spring.homework5.payments.model.Account;
import com.epam.spring.homework5.payments.repository.AccountRepository;
import com.epam.spring.homework5.payments.service.AccountService;
import com.epam.spring.homework5.payments.service.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.INSTANCE.mapAccountDtoToAccount(accountDto);

        account = accountRepository.save(account);
        log.info("create account with number {}", account.getNumber());
        return AccountMapper.INSTANCE.mapAccountToAccountDto(account);
    }

    @Override
    public AccountDto getAccount(Long accountId) {
        log.info("get account by id {}", accountId);
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException(format("Account with id %s is not found", accountId)));
        return AccountMapper.INSTANCE.mapAccountToAccountDto(account);
    }

    @Override
    public AccountDto updateAccount(Long accountId, AccountDto updatedAccountDto) {
        if (!accountRepository.existsById(accountId))
            throw new EntityNotFoundException(format("Account with id %s is not found", accountId));

        log.info("update account by id {}", accountId);
        updatedAccountDto.setAccountId(accountId);
        Account updatedAccount = AccountMapper.INSTANCE.mapAccountDtoToAccount(updatedAccountDto);
        updatedAccount = accountRepository.save(updatedAccount);
        return AccountMapper.INSTANCE.mapAccountToAccountDto(updatedAccount);
    }

    @Override
    public void deleteAccount(Long accountId) {
        if (!accountRepository.existsById(accountId))
            throw new EntityNotFoundException(format("Account with id %s is not found", accountId));

        log.info("delete account by id {}", accountId);
        accountRepository.deleteById(accountId);
    }

    @Override
    public List<AccountDto> listAccounts(Pageable pageable) {
        log.info("get all accounts");
        List<Account> accounts = accountRepository.findAll(pageable).getContent();
        return AccountMapper.INSTANCE.mapAccountToAccountDtos(accounts);
    }

}
