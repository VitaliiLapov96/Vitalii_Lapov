package com.epam.spring.homework6.payments.service.mapper;

import com.epam.spring.homework6.payments.controller.dto.AccountDto;
import com.epam.spring.homework6.payments.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto mapAccountToAccountDto(Account account);
    Account mapAccountDtoToAccount(AccountDto accountDto);
    List<AccountDto> mapAccountToAccountDtos(List<Account> accounts);

}
