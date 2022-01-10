package com.epam.spring.homework3.payments.service.mapper;

import com.epam.spring.homework3.payments.controller.dto.AccountDto;
import com.epam.spring.homework3.payments.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountDto mapAccountToAccountDto(Account account);

    Account mapAccountDtoToAccount(AccountDto accountDto);

}
