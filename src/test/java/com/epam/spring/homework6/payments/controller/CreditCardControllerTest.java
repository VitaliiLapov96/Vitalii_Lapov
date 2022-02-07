package com.epam.spring.homework6.payments.controller;

import com.epam.spring.homework6.payments.controller.dto.AccountDto;
import com.epam.spring.homework6.payments.controller.dto.CreditCardDto;
import com.epam.spring.homework6.payments.exception.EntityNotFoundException;
import com.epam.spring.homework6.payments.service.CreditCardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class CreditCardControllerTest {

    @MockBean
    private CreditCardService creditCardService;

    @Mock
    private Pageable pageable;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private CreditCardDto creditCard;

    private static final Long MOCK_ID = 1L;
    private static final String CREDIT_CARD = "Credit card";

    @BeforeEach
    void setUp() {
        creditCard = CreditCardDto.builder()
                .creditCardId(MOCK_ID)
                .account(AccountDto.builder().accountId(MOCK_ID).build())
                .number("1888444766662222")
                .cvvCode(123L)
                .pinCode(5566L)
                .paymentSystem("Visa")
                .build();
    }

    @Test
    void createCreditCardTest() throws Exception {
        when(creditCardService.createCreditCard(creditCard)).thenReturn(creditCard);

        mockMvc.perform(post("/credit-card")
                .content(mapper.writeValueAsBytes(creditCard))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.creditCardId", is(creditCard.getCreditCardId().intValue())))
                .andExpect(jsonPath("$.number", is(creditCard.getNumber())))
                .andExpect(jsonPath("$.cvvCode", is(creditCard.getCvvCode().intValue())))
                .andExpect(jsonPath("$.pinCode", is(creditCard.getPinCode().intValue())))
                .andExpect(jsonPath("$.paymentSystem", is(creditCard.getPaymentSystem())));
    }

    @Test
    void shouldThrowExceptionWhenCreateCreditCardDoesNotExist() throws Exception {
        doThrow(new EntityNotFoundException(CREDIT_CARD, MOCK_ID)).when(creditCardService).createCreditCard(creditCard);

        mockMvc.perform(post("/creditCard")
                        .content(mapper.writeValueAsBytes(creditCard))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateCreditCardTest() throws Exception {
        when(creditCardService.updateCreditCard(MOCK_ID, creditCard)).thenReturn(creditCard);

        mockMvc.perform(put("/credit-card/{id}", MOCK_ID)
                        .content(mapper.writeValueAsBytes(creditCard))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.creditCardId", is(creditCard.getCreditCardId().intValue())))
                .andExpect(jsonPath("$.number", is(creditCard.getNumber())))
                .andExpect(jsonPath("$.cvvCode", is(creditCard.getCvvCode().intValue())))
                .andExpect(jsonPath("$.pinCode", is(creditCard.getPinCode().intValue())))
                .andExpect(jsonPath("$.paymentSystem", is(creditCard.getPaymentSystem())));
    }

    @Test
    void shouldThrowExceptionWhenUpdateCreditCardDoesNotExist() throws Exception {
        doThrow(new EntityNotFoundException(CREDIT_CARD, MOCK_ID))
                .when(creditCardService).updateCreditCard(MOCK_ID, creditCard);

        mockMvc.perform(put("/credit-card/{id}", MOCK_ID)
                        .content(mapper.writeValueAsBytes(creditCard))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getCreditCardTest() throws Exception {
        when(creditCardService.getCreditCard(MOCK_ID)).thenReturn(creditCard);

        mockMvc.perform(get("/credit-card/{id}", MOCK_ID)
                .content(mapper.writeValueAsBytes(creditCard))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.creditCardId", is(creditCard.getCreditCardId().intValue())))
                .andExpect(jsonPath("$.number", is(creditCard.getNumber())))
                .andExpect(jsonPath("$.cvvCode", is(creditCard.getCvvCode().intValue())))
                .andExpect(jsonPath("$.pinCode", is(creditCard.getPinCode().intValue())))
                .andExpect(jsonPath("$.paymentSystem", is(creditCard.getPaymentSystem())));
    }

    @Test
    void shouldThrowExceptionWhenGetCreditCardDoesNotExist() throws Exception {
        doThrow(new EntityNotFoundException(CREDIT_CARD, MOCK_ID)).when(creditCardService).getCreditCard(MOCK_ID);

        mockMvc.perform(get("/credit-card/{id}", MOCK_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getListCreditCardsTest() throws Exception {
        List<CreditCardDto> expectedCreditCard = new ArrayList<>();
        expectedCreditCard.add(creditCard);

        when(creditCardService.listCreditCards(pageable)).thenReturn(expectedCreditCard);

        mockMvc.perform(get("/credit-card")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCreditCardTest() throws Exception {
        doNothing().when(creditCardService).deleteCreditCard(MOCK_ID);

        mockMvc.perform(delete("/credit-card/{id}", MOCK_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldThrowExceptionWhenDeleteCreditCardDoesNotExist() throws Exception {
        doThrow(new EntityNotFoundException(CREDIT_CARD, MOCK_ID)).when(creditCardService).deleteCreditCard(MOCK_ID);

        mockMvc.perform(delete("/credit-card/{id}", MOCK_ID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}