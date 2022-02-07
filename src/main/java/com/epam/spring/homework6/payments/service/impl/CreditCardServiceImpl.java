package com.epam.spring.homework6.payments.service.impl;

import com.epam.spring.homework6.payments.controller.dto.CreditCardDto;
import com.epam.spring.homework6.payments.exception.EntityNotFoundException;
import com.epam.spring.homework6.payments.model.CreditCard;
import com.epam.spring.homework6.payments.repository.CreditCardRepository;
import com.epam.spring.homework6.payments.service.CreditCardService;
import com.epam.spring.homework6.payments.service.mapper.CreditCardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;

    @Override
    public CreditCardDto createCreditCard(CreditCardDto creditCardDto) {
        CreditCard creditCard = CreditCardMapper.INSTANCE.mapCreditCardDtoToCreditCard(creditCardDto);
        creditCard = creditCardRepository.save(creditCard);
        log.info("create credit card with id {}", creditCard.getCreditCardId());
        return CreditCardMapper.INSTANCE.mapCreditCardToCreditCardDto(creditCard);
    }

    @Override
    public CreditCardDto getCreditCard(Long creditCardId) {
        log.info("get credit card by id {}", creditCardId);
        CreditCard creditCard = creditCardRepository.findById(creditCardId)
                .orElseThrow(() -> new EntityNotFoundException("CreditCard", creditCardId));
        return CreditCardMapper.INSTANCE.mapCreditCardToCreditCardDto(creditCard);
    }

    @Override
    public CreditCardDto updateCreditCard(Long creditCardId, CreditCardDto updatedCreditCardDto) {
        creditCardRepository.findById(creditCardId)
                .orElseThrow(() -> new EntityNotFoundException("CreditCard", creditCardId));

        log.info("update credit card by id {}", creditCardId);
        updatedCreditCardDto.setCreditCardId(creditCardId);
        CreditCard updatedCreditCard = CreditCardMapper.INSTANCE.mapCreditCardDtoToCreditCard(updatedCreditCardDto);
        updatedCreditCard = creditCardRepository.save(updatedCreditCard);
        return CreditCardMapper.INSTANCE.mapCreditCardToCreditCardDto(updatedCreditCard);
    }

    @Override
    public void deleteCreditCard(Long creditCardId) {
        log.info("delete credit card by id {}", creditCardId);
        creditCardRepository.findById(creditCardId)
                .orElseThrow(() -> new EntityNotFoundException("CreditCard", creditCardId));

        creditCardRepository.deleteById(creditCardId);
    }

    @Override
    public List<CreditCardDto> listCreditCards(Pageable pageable) {
        log.info("get all credit cards");
        List<CreditCard> creditCards = creditCardRepository.findAll(pageable).getContent();
        return CreditCardMapper.INSTANCE.mapCreditCardToCreditCardDtos(creditCards);
    }

}
