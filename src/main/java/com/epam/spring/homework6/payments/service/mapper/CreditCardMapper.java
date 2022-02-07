package com.epam.spring.homework6.payments.service.mapper;

import com.epam.spring.homework6.payments.controller.dto.CreditCardDto;
import com.epam.spring.homework6.payments.model.CreditCard;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CreditCardMapper {

    CreditCardMapper INSTANCE = Mappers.getMapper(CreditCardMapper.class);

    CreditCardDto mapCreditCardToCreditCardDto(CreditCard creditCard);

    CreditCard mapCreditCardDtoToCreditCard(CreditCardDto creditCardDto);

    List<CreditCardDto> mapCreditCardToCreditCardDtos(List<CreditCard> creditCards);

}
