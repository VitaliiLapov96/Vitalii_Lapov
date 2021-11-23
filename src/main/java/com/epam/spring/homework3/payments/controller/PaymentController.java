package com.epam.spring.homework3.payments.controller;

import com.epam.spring.homework3.payments.controller.dto.PaymentDto;
import com.epam.spring.homework3.payments.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/payment")
    public PaymentDto createPayment(@RequestBody PaymentDto paymentDto) {
        return paymentService.createPayment(paymentDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/payment/{paymentId}")
    public PaymentDto getPayment(@PathVariable int paymentId) {
        return paymentService.getPayment(paymentId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/payment/{paymentId}")
    public PaymentDto updatePayment(@PathVariable int paymentId, @RequestBody PaymentDto paymentDto) {
        return paymentService.updatePayment(paymentId, paymentDto);
    }

    @DeleteMapping("/payment/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable int paymentId) {
        paymentService.deletePayment(paymentId);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/payment")
    public List<PaymentDto> listPayments() {
        return paymentService.listPayments();
    }

}
