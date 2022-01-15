package com.epam.spring.homework4.payments.service.impl;

import com.epam.spring.homework4.payments.controller.dto.CreditCardDto;
import com.epam.spring.homework4.payments.model.CreditCard;
import com.epam.spring.homework4.payments.repository.CreditCardRepository;
import com.epam.spring.homework4.payments.service.CreditCardService;
import com.epam.spring.homework4.payments.service.mapper.CreditCardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;

    @Override
    public CreditCardDto createCreditCard(CreditCardDto creditCardDto) {
        CreditCard creditCard = CreditCardMapper.INSTANCE.mapCreditCardDtoToCreditCard(creditCardDto);
        creditCard = creditCardRepository.createCreditCard(creditCard);
        log.info("create credit card with id {}", creditCard.getCreditCardId());
        return CreditCardMapper.INSTANCE.mapCreditCardToCreditCardDto(creditCard);
    }

    @Override
    public CreditCardDto getCreditCard(Long creditCardId) {
        log.info("get credit card by id {}", creditCardId);
        CreditCard creditCard = creditCardRepository.getCreditCard(creditCardId);
        return CreditCardMapper.INSTANCE.mapCreditCardToCreditCardDto(creditCard);
    }

    @Override
    public CreditCardDto updateCreditCard(Long creditCardId, CreditCardDto updatedCreditCardDto) {
        log.info("update credit card by id {}", creditCardId);
        CreditCard updatedCreditCard = CreditCardMapper.INSTANCE.mapCreditCardDtoToCreditCard(updatedCreditCardDto);
        updatedCreditCard = creditCardRepository.updateCreditCard(creditCardId, updatedCreditCard);
        return CreditCardMapper.INSTANCE.mapCreditCardToCreditCardDto(updatedCreditCard);
    }

    @Override
    public void deleteCreditCard(Long creditCardId) {
        log.info("delete credit card by id {}", creditCardId);
        creditCardRepository.deleteCreditCard(creditCardId);
    }

    @Override
    public List<CreditCardDto> listCreditCards() {
        log.info("get all credit cards");
        return creditCardRepository.listCreditCards().stream()
                .map(CreditCardMapper.INSTANCE::mapCreditCardToCreditCardDto)
                .collect(Collectors.toList());
    }

}
