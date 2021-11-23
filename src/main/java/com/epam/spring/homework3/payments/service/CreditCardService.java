package com.epam.spring.homework3.payments.service;

import com.epam.spring.homework3.payments.controller.dto.CreditCardDto;

import java.util.List;

public interface CreditCardService {

    CreditCardDto createCreditCard(CreditCardDto creditCardDto);
    CreditCardDto getCreditCard(int creditCardId);
    CreditCardDto updateCreditCard(int creditCardId, CreditCardDto updatedCreditCardDto);
    void deleteCreditCard(int creditCardId);
    List<CreditCardDto> listCreditCards();

}
