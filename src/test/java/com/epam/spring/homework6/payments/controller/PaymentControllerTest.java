package com.epam.spring.homework6.payments.controller;

import com.epam.spring.homework6.payments.controller.dto.AccountDto;
import com.epam.spring.homework6.payments.controller.dto.PaymentDto;
import com.epam.spring.homework6.payments.exception.EntityNotFoundException;
import com.epam.spring.homework6.payments.service.PaymentService;
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
class PaymentControllerTest {

    @MockBean
    private PaymentService paymentService;

    @Mock
    private Pageable pageable;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    private PaymentDto payment;

    private static final Long MOCK_ID = 1L;
    private static final String PAYMENT = "Payment";

    @BeforeEach
    void setUp() {
        payment = PaymentDto.builder()
                .paymentId(MOCK_ID)
                .accountFrom(AccountDto.builder().accountId(2L).build())
                .accountTo(AccountDto.builder().accountId(3L).build())
                .number("00015355165")
                .currency("UAH")
                .build();
    }

    @Test
    void createPaymentTest() throws Exception {
        when(paymentService.createPayment(payment)).thenReturn(payment);

        mockMvc.perform(post("/payment")
                        .content(mapper.writeValueAsBytes(payment))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldThrowExceptionWhenCreatePaymentDoesNotExist() throws Exception {
        doThrow(new EntityNotFoundException(PAYMENT, MOCK_ID)).when(paymentService).createPayment(payment);

        mockMvc.perform(post("/payment")
                        .content(mapper.writeValueAsBytes(payment))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void updatePaymentTest() throws Exception {
        when(paymentService.updatePayment(MOCK_ID, payment)).thenReturn(payment);

        mockMvc.perform(put("/payment/{id}", MOCK_ID)
                        .content(mapper.writeValueAsBytes(payment))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paymentId", is(payment.getPaymentId().intValue())))
                .andExpect(jsonPath("$.number", is(payment.getNumber())))
                .andExpect(jsonPath("$.currency", is(payment.getCurrency())));
    }

    @Test
    void shouldThrowExceptionWhenUpdatePaymentDoesNotExist() throws Exception {
        doThrow(new EntityNotFoundException(PAYMENT, MOCK_ID)).when(paymentService).updatePayment(MOCK_ID, payment);

        mockMvc.perform(put("/payment/{id}", MOCK_ID)
                        .content(mapper.writeValueAsBytes(payment))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getPaymentTest() throws Exception {
        when(paymentService.getPayment(MOCK_ID)).thenReturn(payment);

        mockMvc.perform(get("/payment/{id}", MOCK_ID)
                        .content(mapper.writeValueAsBytes(payment))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paymentId", is(payment.getPaymentId().intValue())))
                .andExpect(jsonPath("$.number", is(payment.getNumber())))
                .andExpect(jsonPath("$.currency", is(payment.getCurrency())));
    }

    @Test
    void shouldThrowExceptionWhenGetPaymentDoesNotExist() throws Exception {
        doThrow(new EntityNotFoundException(PAYMENT, MOCK_ID)).when(paymentService).getPayment(MOCK_ID);

        mockMvc.perform(get("/payment/{id}", MOCK_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getListPaymentsTest() throws Exception {
        List<PaymentDto> expectedPayments = new ArrayList<>();
        expectedPayments.add(payment);

        when(paymentService.listPayments(pageable)).thenReturn(expectedPayments);

        mockMvc.perform(get("/payment")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deletePaymentTest() throws Exception {
        doNothing().when(paymentService).deletePayment(MOCK_ID);

        mockMvc.perform(delete("/payment/{id}", MOCK_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldThrowExceptionWhenDeletePaymentDoesNotExist() throws Exception {
        doThrow(new EntityNotFoundException(PAYMENT, MOCK_ID)).when(paymentService).deletePayment(MOCK_ID);

        mockMvc.perform(delete("/payment/{id}", MOCK_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}