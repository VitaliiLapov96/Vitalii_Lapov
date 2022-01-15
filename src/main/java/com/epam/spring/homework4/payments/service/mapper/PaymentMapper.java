package com.epam.spring.homework4.payments.service.mapper;

import com.epam.spring.homework4.payments.controller.dto.PaymentDto;
import com.epam.spring.homework4.payments.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    PaymentDto mapPaymentToPaymentDto(Payment payment);

    Payment mapPaymentDtoToPayment(PaymentDto paymentDto);

}
