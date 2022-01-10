package com.epam.spring.homework3.payments.repository.impl;

import com.epam.spring.homework3.payments.model.Account;
import com.epam.spring.homework3.payments.repository.AccountRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AccountRepositoryImpl implements AccountRepository {

    private final List<Account> accountList = new ArrayList<>();
    private static Long ACCOUNT_COUNT = 0L;

    @Override
    public Account createAccount(Account account) {
        account.setAccountId(++ACCOUNT_COUNT);
        account.setDate(LocalDate.now());
        accountList.add(account);
        return account;
    }

    @Override
    public Account getAccount(Long accountId) {
        return accountList.stream()
                .filter(account -> Objects.equals(account.getAccountId(), accountId))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Account is not found"));
    }

    @Override
    public Account updateAccount(Long accountId, Account updatedAccount) {
        Account account = accountList.stream()
                .filter(nextAccount -> Objects.equals(nextAccount.getAccountId(), accountId))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Account is not updated"));

        account.setUserId(updatedAccount.getUserId());
        account.setCreditLimit(updatedAccount.getCreditLimit());
        account.setBalance(updatedAccount.getBalance());
        account.setName(updatedAccount.getName());
        account.setNumber(updatedAccount.getNumber());
        account.setCurrency(updatedAccount.getCurrency());
        account.setDate(updatedAccount.getDate());
        return account;
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountList.removeIf(account -> Objects.equals(account.getAccountId(), accountId));
    }

    @Override
    public List<Account> listAccounts() {
        return new ArrayList<>(accountList);
    }

}
