package com.epam.spring.homework3.payments.repository.impl;

import com.epam.spring.homework3.payments.model.CreditCard;
import com.epam.spring.homework3.payments.repository.CreditCardRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreditCardRepositoryImpl implements CreditCardRepository {

    private final List<CreditCard> creditCardList = new ArrayList<>();
    private static int CREDIT_CARD_ID_COUNT = 0;

    @Override
    public CreditCard createCreditCard(CreditCard creditCard) {
        creditCard.setCreditCardId(++CREDIT_CARD_ID_COUNT);
        creditCard.setDate(LocalDate.now());
        creditCardList.add(creditCard);
        return creditCard;
    }

    @Override
    public CreditCard getCreditCard(int creditCardId) {
        return creditCardList.stream()
                .filter(creditCard -> creditCard.getCreditCardId() == creditCardId)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Credit card is not found"));
    }

    @Override
    public CreditCard updateCreditCard(int creditCardId, CreditCard updatedCreditCard) {
        boolean creditCardIsDeleted = creditCardList
                .removeIf(creditCard -> creditCard.getCreditCardId() == creditCardId);
        if (!creditCardIsDeleted)
            throw new RuntimeException("Credit card is not updated");

        updatedCreditCard.setCreditCardId(++CREDIT_CARD_ID_COUNT);
        updatedCreditCard.setDate(LocalDate.now());
        creditCardList.add(updatedCreditCard);
        return updatedCreditCard;
    }

    @Override
    public void deleteCreditCard(int creditCardId) {
        creditCardList.removeIf(creditCard -> creditCard.getCreditCardId() == creditCardId);
    }

    @Override
    public List<CreditCard> listCreditCards() {
        return new ArrayList<>(creditCardList);
    }

}
