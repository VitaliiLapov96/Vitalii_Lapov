package com.epam.spring.homework6.payments.service.impl;

import com.epam.spring.homework6.payments.controller.dto.CreditCardDto;
import com.epam.spring.homework6.payments.controller.dto.PaymentDto;
import com.epam.spring.homework6.payments.exception.EntityNotFoundException;
import com.epam.spring.homework6.payments.model.Payment;
import com.epam.spring.homework6.payments.repository.PaymentRepository;
import com.epam.spring.homework6.payments.service.mapper.PaymentMapper;
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
class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private Pageable pageable;

    @Mock
    private Page<Payment> pageResult;

    private Payment expectedPayment;

    private static final Long MOCK_ID = 1L;

    @BeforeEach
    void setUp() {
        expectedPayment = Payment.builder().paymentId(MOCK_ID).build();
    }

    @Test
    void shouldCreatePayment() {
        //given
        when(paymentRepository.save(expectedPayment)).thenReturn(expectedPayment);

        //when
        PaymentDto actualPayment = paymentService
                .createPayment(PaymentMapper.INSTANCE.mapPaymentToPaymentDto(expectedPayment));

        //then
        assertEquals(expectedPayment.getPaymentId(), actualPayment.getPaymentId());
    }

    @Test
    void shouldReturnPayment() {
        //given
        when(paymentRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedPayment));

        //when
        PaymentDto actualPayment = paymentService.getPayment(MOCK_ID);

        //then
        assertEquals(expectedPayment.getPaymentId(), actualPayment.getPaymentId());
    }

    @Test
    void shouldThrowExceptionWhenReturnPaymentDoesNotExist() {
        when(paymentRepository.findById(MOCK_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> paymentService.getPayment(MOCK_ID));
    }

    @Test
    void shouldReturnAllPayments() {
        //given
        List<Payment> expectedPayments = new ArrayList<>();
        expectedPayments.add(expectedPayment);
        when(paymentRepository.findAll(pageable)).thenReturn(pageResult);
        when(pageResult.getContent()).thenReturn(expectedPayments);

        //when
        List<PaymentDto> actualPayments = paymentService.listPayments(pageable);

        //then
        assertThat(actualPayments, hasSize(1));
    }

    @Test
    void shouldUpdatePayment() {
        //given
        PaymentDto expectedPaymentDto = PaymentDto.builder().number("1111111111111").build();
        when(paymentRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedPayment));

        //when
        paymentService.updatePayment(MOCK_ID, expectedPaymentDto);

        //then
        verify(paymentRepository).save(PaymentMapper.INSTANCE.mapPaymentDtoToPayment(expectedPaymentDto));
        verify(paymentRepository).findById(MOCK_ID);
    }

    @Test
    void shouldThrowExceptionWhenUpdatePaymentDoesNotExist() {
        PaymentDto expectedPaymentDto = PaymentDto.builder().number("1111111111111").build();

        when(paymentRepository.findById(MOCK_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> paymentService.updatePayment(MOCK_ID, expectedPaymentDto));
    }

    @Test
    void shouldDeletePayment() {
        //given
        when(paymentRepository.findById(MOCK_ID)).thenReturn(Optional.of(expectedPayment));
        doNothing().when(paymentRepository).deleteById(MOCK_ID);

        //when
        paymentService.deletePayment(MOCK_ID);

        //then
        verify(paymentRepository, times(1)).deleteById(MOCK_ID);
    }

    @Test
    void shouldThrowExceptionWhenDeletePaymentDoesNotExist() {
        when(paymentRepository.findById(MOCK_ID)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> paymentService.deletePayment(MOCK_ID));
    }

}