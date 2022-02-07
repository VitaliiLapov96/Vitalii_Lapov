package com.epam.spring.homework6.payments.service.impl;

import com.epam.spring.homework6.payments.controller.dto.AccountDto;
import com.epam.spring.homework6.payments.controller.dto.CreditCardDto;
import com.epam.spring.homework6.payments.exception.EntityNotFoundException;
import com.epam.spring.homework6.payments.model.CreditCard;
import com.epam.spring.homework6.payments.repository.CreditCardRepository;
import com.epam.spring.homework6.payments.service.mapper.CreditCardMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreditCardServiceImplTest {

    @InjectMocks
    private CreditCardServiceImpl creditCardService;

    @Mock
    private CreditCardRepository creditCardRepository;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<CreditCard> pageResult;

    private CreditCard expectedCreditCard;

    private static final Long MOCK_ID = 1L;

    @BeforeEach
    void setUp() {
        expectedCreditCard = CreditCard.builder().creditCardId(MOCK_ID).build();
    }

    @Test
    void shouldCreateCreditCard() {
        //given
        when(creditCardRepository.save(expectedCreditCard)).thenReturn(expectedCreditCard);

        //when
        CreditCardDto actualCreditCard = creditCardService
                .createCreditCard(CreditCardMapper.INSTANCE.mapCreditCardToCreditCardDto(expectedCreditCard));

        //then
        assertEquals(expectedCreditCard.getCreditCardId(), actualCreditCard.getCreditCardId());
    }

    @Test
    void shouldReturnCreditCard() {
        //given
        when(creditCardRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedCreditCard));

        //when
        CreditCardDto actualCreditCard = creditCardService.getCreditCard(MOCK_ID);

        //then
        assertEquals(expectedCreditCard.getCreditCardId(), actualCreditCard.getCreditCardId());
    }

    @Test
    void shouldThrowExceptionWhenReturnCreditCardDoesNotExist() {
        when(creditCardRepository.findById(MOCK_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> creditCardService.getCreditCard(MOCK_ID));
    }

    @Test
    void shouldReturnAllCreditCards() {
        //given
        List<CreditCard> expectedCreditCards = new ArrayList<>();
        expectedCreditCards.add(expectedCreditCard);
        when(creditCardRepository.findAll(pageable)).thenReturn(pageResult);
        when(pageResult.getContent()).thenReturn(expectedCreditCards);

        //when
        List<CreditCardDto> actualCreditCards = creditCardService.listCreditCards(pageable);

        //then
        assertThat(actualCreditCards, hasSize(1));
    }

    @Test
    void shouldUpdateCreditCard() {
        //given
        CreditCardDto expectedCreditCardDto = CreditCardDto.builder().number("77777777777").build();
        when(creditCardRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedCreditCard));

        //when
        creditCardService.updateCreditCard(MOCK_ID, expectedCreditCardDto);

        //then
        verify(creditCardRepository).save(CreditCardMapper.INSTANCE.mapCreditCardDtoToCreditCard(expectedCreditCardDto));
        verify(creditCardRepository).findById(MOCK_ID);
    }

    @Test
    void shouldThrowExceptionWhenUpdateCreditCardDoesNotExist() {
        CreditCardDto expectedCreditCardDto = CreditCardDto.builder().number("77777777777").build();

        when(creditCardRepository.findById(MOCK_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class,
                () -> creditCardService.updateCreditCard(MOCK_ID, expectedCreditCardDto));
    }

    @Test
    void shouldDeleteCreditCard() {
        //given
        when(creditCardRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedCreditCard));
        doNothing().when(creditCardRepository).deleteById(MOCK_ID);

        //when
        creditCardService.deleteCreditCard(MOCK_ID);

        //then
        verify(creditCardRepository, times(1)).deleteById(MOCK_ID);
    }

    @Test
    void shouldThrowExceptionWhenDeleteCreditCardDoesNotExist() {
        when(creditCardRepository.findById(MOCK_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> creditCardService.deleteCreditCard(MOCK_ID));
    }

}