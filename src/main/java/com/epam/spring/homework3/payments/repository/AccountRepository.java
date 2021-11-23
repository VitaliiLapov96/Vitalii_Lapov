package com.epam.spring.homework3.payments.repository;

import com.epam.spring.homework3.payments.model.Account;

import java.util.List;

public interface AccountRepository {

    Account createAccount(Account account);
    Account getAccount(int accountId);
    Account updateAccount(int accountId, Account updatedAccount);
    void deleteAccount(int accountId);
    List<Account> listAccounts();

}
