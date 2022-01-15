package com.epam.spring.homework4.payments.repository.impl;

import com.epam.spring.homework4.payments.model.Payment;
import com.epam.spring.homework4.payments.repository.PaymentRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class PaymentRepositoryImpl implements PaymentRepository {

    private final List<Payment> paymentList = new ArrayList<>();
    private static Long PAYMENT_ID_COUNT = 0L;

    @Override
    public Payment createPayment(Payment payment) {
        payment.setPaymentId(++PAYMENT_ID_COUNT);
        payment.setDate(LocalDate.now());
        paymentList.add(payment);
        return payment;
    }

    @Override
    public Payment getPayment(Long paymentId) {
        return paymentList.stream()
                .filter(payment -> Objects.equals(payment.getPaymentId(), paymentId))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Payment is not found"));
    }

    @Override
    public Payment updatePayment(Long paymentId, Payment updatedPayment) {
        Payment payment = paymentList.stream()
                .filter(nextPayment -> Objects.equals(nextPayment.getPaymentId(), paymentId))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Payment is not updated"));

        payment.setAccountIdFrom(updatedPayment.getAccountIdFrom());
        payment.setAccountIdTo(updatedPayment.getAccountIdTo());
        payment.setAmount(updatedPayment.getAmount());
        payment.setNumber(updatedPayment.getNumber());
        payment.setDescription(updatedPayment.getDescription());
        payment.setCurrency(updatedPayment.getCurrency());
        payment.setDate(updatedPayment.getDate());
        return payment;
    }

    @Override
    public void deletePayment(Long paymentId) {
        paymentList.removeIf(payment -> Objects.equals(payment.getPaymentId(), paymentId));
    }

    @Override
    public List<Payment> listPayments() {
        return new ArrayList<>(paymentList);
    }

}
