package com.epam.spring.homework3.payments.service;

import com.epam.spring.homework3.payments.controller.dto.CreditCardDto;

import java.util.List;

public interface CreditCardService {

    CreditCardDto createCreditCard(CreditCardDto creditCardDto);
    CreditCardDto getCreditCard(Long creditCardId);
    CreditCardDto updateCreditCard(Long creditCardId, CreditCardDto updatedCreditCardDto);
    void deleteCreditCard(Long creditCardId);
    List<CreditCardDto> listCreditCards();

}
