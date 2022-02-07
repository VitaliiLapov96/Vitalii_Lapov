package com.epam.spring.homework6.payments.controller;

import com.epam.spring.homework6.payments.controller.dto.AccountDto;
import com.epam.spring.homework6.payments.service.AccountService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequiredArgsConstructor
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class AccountController {

    private final AccountService accountService;

    @ApiOperation("Create account")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/account")
    public AccountDto createAccount(@RequestBody @Valid AccountDto accountDto) {
        return accountService.createAccount(accountDto);
    }

    @ApiOperation("Get account by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/account/{accountId}")
    public AccountDto getAccount(@PathVariable @Min(1) Long accountId) {
        return accountService.getAccount(accountId);
    }

    @ApiOperation("Update account by id")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/account/{accountId}")
    public AccountDto updateAccount(@PathVariable @Min(1) Long accountId,
                                    @RequestBody @Valid AccountDto updatedAccountDto) {
        return accountService.updateAccount(accountId, updatedAccountDto);
    }

    @ApiOperation("Delete account by id")
    @DeleteMapping("/account/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable @Min(1) Long accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Get accounts")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/account")
    public List<AccountDto> listAccounts(@RequestParam(defaultValue = "0") @Min(0) Integer page,
                                         @RequestParam(defaultValue = "10") @Min(10) Integer pageSize,
                                         @RequestParam(defaultValue = "accountId") String sortBy) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sortBy));
        return accountService.listAccounts(pageable);
    }

}
