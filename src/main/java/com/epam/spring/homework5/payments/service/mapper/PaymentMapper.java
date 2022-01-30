package com.epam.spring.homework5.payments.service.mapper;

import com.epam.spring.homework5.payments.controller.dto.PaymentDto;
import com.epam.spring.homework5.payments.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    PaymentDto mapPaymentToPaymentDto(Payment payment);
    Payment mapPaymentDtoToPayment(PaymentDto paymentDto);
    List<PaymentDto> mapPaymentToPaymentDtos(List<Payment> payments);

}
