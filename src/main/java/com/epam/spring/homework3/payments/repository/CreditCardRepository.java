package com.epam.spring.homework3.payments.repository;


import com.epam.spring.homework3.payments.model.CreditCard;

import java.util.List;

public interface CreditCardRepository {

    CreditCard createCreditCard(CreditCard creditCard);
    CreditCard getCreditCard(int creditCardId);
    CreditCard updateCreditCard(int creditCardId, CreditCard updatedCreditCard);
    void deleteCreditCard(int creditCardId);
    List<CreditCard> listCreditCards();

}
