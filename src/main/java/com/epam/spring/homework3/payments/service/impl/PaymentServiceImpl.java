package com.epam.spring.homework3.payments.service.impl;

import com.epam.spring.homework3.payments.controller.dto.PaymentDto;
import com.epam.spring.homework3.payments.model.Payment;
import com.epam.spring.homework3.payments.repository.PaymentRepository;
import com.epam.spring.homework3.payments.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        log.info("create payment with id {}", paymentDto.getPaymentId());
        Payment payment = mapPaymentDtoToPayment(paymentDto);
        payment = paymentRepository.createPayment(payment);
        return mapPaymentToPaymentDto(payment);
    }

    @Override
    public PaymentDto getPayment(int paymentId) {
        log.info("get payment with id {}", paymentId);
        Payment payment = paymentRepository.getPayment(paymentId);
        return mapPaymentToPaymentDto(payment);
    }

    @Override
    public PaymentDto updatePayment(int paymentId, PaymentDto updatedPaymentDto) {
        log.info("update payment by id {}", paymentId);
        Payment updatedPayment = mapPaymentDtoToPayment(updatedPaymentDto);
        updatedPayment = paymentRepository.updatePayment(paymentId, updatedPayment);
        return mapPaymentToPaymentDto(updatedPayment);
    }

    @Override
    public void deletePayment(int paymentId) {
        log.info("delete payment by id {}", paymentId);
        paymentRepository.deletePayment(paymentId);
    }

    @Override
    public List<PaymentDto> listPayments() {
        log.info("get all payments");
        return paymentRepository.listPayments().stream()
                .map(this::mapPaymentToPaymentDto)
                .collect(Collectors.toList());
    }

    private PaymentDto mapPaymentToPaymentDto(Payment payment) {
        return PaymentDto.builder()
                .paymentId(payment.getPaymentId())
                .accountIdFrom(payment.getAccountIdFrom())
                .accountIdTo(payment.getAccountIdTo())
                .number(payment.getNumber())
                .description(payment.getDescription())
                .currency(payment.getCurrency())
                .date(payment.getDate())
                .build();
    }

    private Payment mapPaymentDtoToPayment(PaymentDto paymentDto) {
        return Payment.builder()
                .paymentId(paymentDto.getPaymentId())
                .accountIdFrom(paymentDto.getAccountIdFrom())
                .accountIdTo(paymentDto.getAccountIdTo())
                .number(paymentDto.getNumber())
                .description(paymentDto.getDescription())
                .currency(paymentDto.getCurrency())
                .date(paymentDto.getDate())
                .build();
    }

}
