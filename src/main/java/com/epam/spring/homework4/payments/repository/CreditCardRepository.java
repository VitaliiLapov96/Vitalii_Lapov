package com.epam.spring.homework4.payments.repository;


import com.epam.spring.homework4.payments.model.CreditCard;

import java.util.List;

public interface CreditCardRepository {

    CreditCard createCreditCard(CreditCard creditCard);
    CreditCard getCreditCard(Long creditCardId);
    CreditCard updateCreditCard(Long creditCardId, CreditCard updatedCreditCard);
    void deleteCreditCard(Long creditCardId);
    List<CreditCard> listCreditCards();

}
