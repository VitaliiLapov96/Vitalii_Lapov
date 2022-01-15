package com.epam.spring.homework4.payments.repository;

import com.epam.spring.homework4.payments.model.Account;

import java.util.List;

public interface AccountRepository {

    Account createAccount(Account account);
    Account getAccount(Long accountId);
    Account updateAccount(Long accountId, Account updatedAccount);
    void deleteAccount(Long accountId);
    List<Account> listAccounts();

}
