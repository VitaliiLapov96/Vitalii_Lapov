package com.epam.spring.homework3.payments.repository.impl;

import com.epam.spring.homework3.payments.model.CreditCard;
import com.epam.spring.homework3.payments.repository.CreditCardRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CreditCardRepositoryImpl implements CreditCardRepository {

    private final List<CreditCard> creditCardList = new ArrayList<>();
    private static Long CREDIT_CARD_ID_COUNT = 0L;

    @Override
    public CreditCard createCreditCard(CreditCard creditCard) {
        creditCard.setCreditCardId(++CREDIT_CARD_ID_COUNT);
        creditCard.setDate(LocalDate.now());
        creditCardList.add(creditCard);
        return creditCard;
    }

    @Override
    public CreditCard getCreditCard(Long creditCardId) {
        return creditCardList.stream()
                .filter(creditCard -> Objects.equals(creditCard.getCreditCardId(), creditCardId))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Credit card is not found"));
    }

    @Override
    public CreditCard updateCreditCard(Long creditCardId, CreditCard updatedCreditCard) {
        CreditCard creditCard = creditCardList.stream()
                .filter(nextCreditCard -> Objects.equals(nextCreditCard.getCreditCardId(), creditCardId))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Credit card is not updated"));

        creditCard.setAccountId(updatedCreditCard.getAccountId());
        creditCard.setCvvCode(updatedCreditCard.getCvvCode());
        creditCard.setPinCode(updatedCreditCard.getPinCode());
        creditCard.setNumber(updatedCreditCard.getNumber());
        creditCard.setPaymentSystem(updatedCreditCard.getPaymentSystem());
        creditCard.setDate(updatedCreditCard.getDate());
        return creditCard;
    }

    @Override
    public void deleteCreditCard(Long creditCardId) {
        creditCardList.removeIf(creditCard -> Objects.equals(creditCard.getCreditCardId(), creditCardId));
    }

    @Override
    public List<CreditCard> listCreditCards() {
        return new ArrayList<>(creditCardList);
    }

}
