package com.epam.spring.homework3.payments.controller;

import com.epam.spring.homework3.payments.controller.dto.AccountDto;
import com.epam.spring.homework3.payments.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/account")
    public AccountDto createAccount(@RequestBody AccountDto accountDto) {
        return accountService.createAccount(accountDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/account/{accountId}")
    public AccountDto getAccount(@PathVariable Long accountId) {
        return accountService.getAccount(accountId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/account/{accountId}")
    public AccountDto updateAccount(@PathVariable Long accountId, @RequestBody AccountDto updatedAccountDto) {
        return accountService.updateAccount(accountId, updatedAccountDto);
    }

    @DeleteMapping("/account/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/account")
    public List<AccountDto> listAccounts() {
        return accountService.listAccounts();
    }

}
