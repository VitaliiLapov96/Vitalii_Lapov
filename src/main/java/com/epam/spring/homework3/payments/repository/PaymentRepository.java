package com.epam.spring.homework3.payments.repository;

import com.epam.spring.homework3.payments.model.Payment;

import java.util.List;

public interface PaymentRepository {

    Payment createPayment(Payment payment);
    Payment getPayment(int paymentId);
    Payment updatePayment(int paymentId, Payment updatedPayment);
    void deletePayment(int paymentId);
    List<Payment> listPayments();

}
