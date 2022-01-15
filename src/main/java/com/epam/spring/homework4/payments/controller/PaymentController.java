package com.epam.spring.homework4.payments.controller;

import com.epam.spring.homework4.payments.controller.dto.PaymentDto;
import com.epam.spring.homework4.payments.service.PaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;


@RestController
@RequiredArgsConstructor
@ApiResponses({
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 500, message = "Internal Server Error")
})
public class PaymentController {

    private final PaymentService paymentService;

    @ApiOperation("Create payment")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/payment")
    public PaymentDto createPayment(@RequestBody @Valid PaymentDto paymentDto) {
        return paymentService.createPayment(paymentDto);
    }

    @ApiOperation("Get payment by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/payment/{paymentId}")
    public PaymentDto getPayment(@PathVariable @Min(1) Long paymentId) {
        return paymentService.getPayment(paymentId);
    }

    @ApiOperation("Update payment by id")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/payment/{paymentId}")
    public PaymentDto updatePayment(@PathVariable @Min(1) Long paymentId,
                                    @RequestBody @Valid PaymentDto paymentDto) {
        return paymentService.updatePayment(paymentId, paymentDto);
    }

    @ApiOperation("Delete payment by id")
    @DeleteMapping("/payment/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable @Min(1) Long paymentId) {
        paymentService.deletePayment(paymentId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Get payments")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/payment")
    public List<PaymentDto> listPayments() {
        return paymentService.listPayments();
    }

}
