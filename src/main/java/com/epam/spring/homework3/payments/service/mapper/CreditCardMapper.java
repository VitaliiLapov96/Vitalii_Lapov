package com.epam.spring.homework3.payments.service.mapper;

import com.epam.spring.homework3.payments.controller.dto.CreditCardDto;
import com.epam.spring.homework3.payments.model.CreditCard;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CreditCardMapper {

    CreditCardMapper INSTANCE = Mappers.getMapper(CreditCardMapper.class);

    CreditCardDto mapCreditCardToCreditCardDto(CreditCard creditCard);

    CreditCard mapCreditCardDtoToCreditCard(CreditCardDto creditCardDto);

}
