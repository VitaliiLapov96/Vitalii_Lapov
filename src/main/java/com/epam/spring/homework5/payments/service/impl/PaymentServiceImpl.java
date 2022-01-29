package com.epam.spring.homework5.payments.service.impl;

import com.epam.spring.homework5.payments.controller.dto.PaymentDto;
import com.epam.spring.homework5.payments.exception.EntityNotFoundException;
import com.epam.spring.homework5.payments.model.Payment;
import com.epam.spring.homework5.payments.repository.PaymentRepository;
import com.epam.spring.homework5.payments.service.PaymentService;
import com.epam.spring.homework5.payments.service.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public PaymentDto createPayment(PaymentDto paymentDto) {
        Payment payment = PaymentMapper.INSTANCE.mapPaymentDtoToPayment(paymentDto);
        payment = paymentRepository.save(payment);
        log.info("create payment with id {}", payment.getPaymentId());
        return PaymentMapper.INSTANCE.mapPaymentToPaymentDto(payment);
    }

    @Override
    public PaymentDto getPayment(Long paymentId) {
        log.info("get payment with id {}", paymentId);
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new EntityNotFoundException(format("Payment with id %s is not found", paymentId)));
        return PaymentMapper.INSTANCE.mapPaymentToPaymentDto(payment);
    }

    @Override
    public PaymentDto updatePayment(Long paymentId, PaymentDto updatedPaymentDto) {
        if (!paymentRepository.existsById(paymentId))
            throw new EntityNotFoundException(format("Payment with id %s is not found", paymentId));

        log.info("update payment by id {}", paymentId);
        updatedPaymentDto.setPaymentId(paymentId);
        Payment updatedPayment = PaymentMapper.INSTANCE.mapPaymentDtoToPayment(updatedPaymentDto);
        updatedPayment = paymentRepository.save(updatedPayment);
        return PaymentMapper.INSTANCE.mapPaymentToPaymentDto(updatedPayment);
    }

    @Override
    public void deletePayment(Long paymentId) {
        if (!paymentRepository.existsById(paymentId))
            throw new EntityNotFoundException(format("Payment with id %s is not found", paymentId));

        log.info("delete payment by id {}", paymentId);
        paymentRepository.deleteById(paymentId);
    }

    @Override
    public List<PaymentDto> listPayments(Pageable pageable) {
        log.info("get all payments");
        List<Payment> payments = paymentRepository.findAll(pageable).getContent();
        return PaymentMapper.INSTANCE.mapPaymentToPaymentDtos(payments);
    }

}
