package com.epam.spring.homework4.payments.repository;

import com.epam.spring.homework4.payments.model.Payment;

import java.util.List;

public interface PaymentRepository {

    Payment createPayment(Payment payment);
    Payment getPayment(Long paymentId);
    Payment updatePayment(Long paymentId, Payment updatedPayment);
    void deletePayment(Long paymentId);
    List<Payment> listPayments();

}
