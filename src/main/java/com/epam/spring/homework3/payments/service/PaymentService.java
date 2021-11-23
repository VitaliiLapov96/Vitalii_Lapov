package com.epam.spring.homework3.payments.service;

import com.epam.spring.homework3.payments.controller.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    PaymentDto createPayment(PaymentDto paymentDto);
    PaymentDto getPayment(int paymentId);
    PaymentDto updatePayment(int paymentId, PaymentDto updatedPaymentDto);
    void deletePayment(int paymentId);
    List<PaymentDto> listPayments();

}
