package com.epam.spring.homework4.payments.service.impl;

import com.epam.spring.homework4.payments.controller.dto.PaymentDto;
import com.epam.spring.homework4.payments.model.Payment;
import com.epam.spring.homework4.payments.repository.PaymentRepository;
import com.epam.spring.homework4.payments.service.PaymentService;
import com.epam.spring.homework4.payments.service.mapper.PaymentMapper;
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
        Payment payment = PaymentMapper.INSTANCE.mapPaymentDtoToPayment(paymentDto);
        payment = paymentRepository.createPayment(payment);
        log.info("create payment with id {}", payment.getPaymentId());
        return PaymentMapper.INSTANCE.mapPaymentToPaymentDto(payment);
    }

    @Override
    public PaymentDto getPayment(Long paymentId) {
        log.info("get payment with id {}", paymentId);
        Payment payment = paymentRepository.getPayment(paymentId);
        return PaymentMapper.INSTANCE.mapPaymentToPaymentDto(payment);
    }

    @Override
    public PaymentDto updatePayment(Long paymentId, PaymentDto updatedPaymentDto) {
        log.info("update payment by id {}", paymentId);
        Payment updatedPayment = PaymentMapper.INSTANCE.mapPaymentDtoToPayment(updatedPaymentDto);
        updatedPayment = paymentRepository.updatePayment(paymentId, updatedPayment);
        return PaymentMapper.INSTANCE.mapPaymentToPaymentDto(updatedPayment);
    }

    @Override
    public void deletePayment(Long paymentId) {
        log.info("delete payment by id {}", paymentId);
        paymentRepository.deletePayment(paymentId);
    }

    @Override
    public List<PaymentDto> listPayments() {
        log.info("get all payments");
        return paymentRepository.listPayments().stream()
                .map(PaymentMapper.INSTANCE::mapPaymentToPaymentDto)
                .collect(Collectors.toList());
    }

}
