package com.epam.spring.homework3.payments.service.impl;

import com.epam.spring.homework3.payments.controller.dto.CreditCardDto;
import com.epam.spring.homework3.payments.model.CreditCard;
import com.epam.spring.homework3.payments.repository.CreditCardRepository;
import com.epam.spring.homework3.payments.service.CreditCardService;
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
        CreditCard creditCard = mapCreditCardDtoToCreditCard(creditCardDto);
        creditCard = creditCardRepository.createCreditCard(creditCard);
        log.info("create credit card with id {}", creditCard.getCreditCardId());
        return mapCreditCardToCreditCardDto(creditCard);
    }

    @Override
    public CreditCardDto getCreditCard(Long creditCardId) {
        log.info("get credit card by id {}", creditCardId);
        CreditCard creditCard = creditCardRepository.getCreditCard(creditCardId);
        return mapCreditCardToCreditCardDto(creditCard);
    }

    @Override
    public CreditCardDto updateCreditCard(Long creditCardId, CreditCardDto updatedCreditCardDto) {
        log.info("update credit card by id {}", creditCardId);
        CreditCard updatedCreditCard = mapCreditCardDtoToCreditCard(updatedCreditCardDto);
        updatedCreditCard = creditCardRepository.updateCreditCard(creditCardId, updatedCreditCard);
        return mapCreditCardToCreditCardDto(updatedCreditCard);
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
                .map(this::mapCreditCardToCreditCardDto)
                .collect(Collectors.toList());
    }

    private CreditCardDto mapCreditCardToCreditCardDto(CreditCard creditCard) {
        return CreditCardDto.builder()
                .creditCardId(creditCard.getCreditCardId())
                .accountId(creditCard.getAccountId())
                .cvvCode(creditCard.getCvvCode())
                .pinCode(creditCard.getPinCode())
                .number(creditCard.getNumber())
                .paymentSystem(creditCard.getPaymentSystem())
                .date(creditCard.getDate())
                .build();
    }

    private CreditCard mapCreditCardDtoToCreditCard(CreditCardDto creditCardDto) {
        return CreditCard.builder()
                .creditCardId(creditCardDto.getCreditCardId())
                .accountId(creditCardDto.getAccountId())
                .cvvCode(creditCardDto.getCvvCode())
                .pinCode(creditCardDto.getPinCode())
                .number(creditCardDto.getNumber())
                .paymentSystem(creditCardDto.getPaymentSystem())
                .date(creditCardDto.getDate())
                .build();
    }

}
