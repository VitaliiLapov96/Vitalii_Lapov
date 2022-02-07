package com.epam.spring.homework6.payments.service;

import com.epam.spring.homework6.payments.controller.dto.CreditCardDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CreditCardService {

    CreditCardDto createCreditCard(CreditCardDto creditCardDto);
    CreditCardDto getCreditCard(Long creditCardId);
    CreditCardDto updateCreditCard(Long creditCardId, CreditCardDto updatedCreditCardDto);
    void deleteCreditCard(Long creditCardId);
    List<CreditCardDto> listCreditCards(Pageable pageable);

}
