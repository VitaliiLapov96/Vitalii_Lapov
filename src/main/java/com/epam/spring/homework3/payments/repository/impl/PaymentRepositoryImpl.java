package com.epam.spring.homework3.payments.repository.impl;

import com.epam.spring.homework3.payments.model.Payment;
import com.epam.spring.homework3.payments.repository.PaymentRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentRepositoryImpl implements PaymentRepository {

    private final List<Payment> paymentList = new ArrayList<>();
    private static int PAYMENT_ID_COUNT = 0;

    @Override
    public Payment createPayment(Payment payment) {
        payment.setPaymentId(++PAYMENT_ID_COUNT);
        payment.setDate(LocalDate.now());
        paymentList.add(payment);
        return payment;
    }

    @Override
    public Payment getPayment(int paymentId) {
        return paymentList.stream()
                .filter(payment -> payment.getPaymentId() == paymentId)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Payment is not found"));
    }

    @Override
    public Payment updatePayment(int paymentId, Payment updatedPayment) {
        boolean paymentIsDeleted = paymentList
                .removeIf(payment -> payment.getPaymentId() == paymentId);
        if (!paymentIsDeleted)
            throw new RuntimeException("Payment is not updated");

        updatedPayment.setPaymentId(++PAYMENT_ID_COUNT);
        updatedPayment.setDate(LocalDate.now());
        paymentList.add(updatedPayment);
        return updatedPayment;
    }

    @Override
    public void deletePayment(int paymentId) {
        paymentList.removeIf(payment -> payment.getPaymentId() == paymentId);
    }

    @Override
    public List<Payment> listPayments() {
        return new ArrayList<>(paymentList);
    }

}
