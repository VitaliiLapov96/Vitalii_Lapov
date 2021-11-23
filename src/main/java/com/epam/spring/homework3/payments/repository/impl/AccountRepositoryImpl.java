package com.epam.spring.homework3.payments.repository.impl;

import com.epam.spring.homework3.payments.model.Account;
import com.epam.spring.homework3.payments.repository.AccountRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class AccountRepositoryImpl implements AccountRepository {

    private final List<Account> accountList = new ArrayList<>();
    private static int ACCOUNT_COUNT = 0;

    @Override
    public Account createAccount(Account account) {
        account.setAccountId(++ACCOUNT_COUNT);
        account.setDate(LocalDate.now());
        accountList.add(account);
        return account;
    }

    @Override
    public Account getAccount(int accountId) {
        return accountList.stream()
                .filter(account -> account.getAccountId() == accountId)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Account is not found"));
    }

    @Override
    public Account updateAccount(int accountId, Account updatedAccount) {
        boolean accountIsDeleted = accountList
                .removeIf(account -> account.getAccountId() == accountId);
        if (!accountIsDeleted)
            throw new RuntimeException("Account is not updated");

        updatedAccount.setAccountId(++ACCOUNT_COUNT);
        updatedAccount.setDate(LocalDate.now());
        accountList.add(updatedAccount);
        return updatedAccount;
    }

    @Override
    public void deleteAccount(int accountId) {
        accountList.removeIf(account -> account.getAccountId() == accountId);
    }

    @Override
    public List<Account> listAccounts() {
        return new ArrayList<>(accountList);
    }

}
