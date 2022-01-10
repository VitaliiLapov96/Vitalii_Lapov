package com.epam.spring.homework3.payments.service;

import com.epam.spring.homework3.payments.controller.dto.PaymentDto;

import java.util.List;

public interface PaymentService {

    PaymentDto createPayment(PaymentDto paymentDto);
    PaymentDto getPayment(Long paymentId);
    PaymentDto updatePayment(Long paymentId, PaymentDto updatedPaymentDto);
    void deletePayment(Long paymentId);
    List<PaymentDto> listPayments();

}
