package com.epam.spring.homework5.payments.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class CreditCard implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long creditCardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    private Long cvvCode;

    private Long pinCode;

    @Column(nullable = false, unique = true)
    private String number;

    private String paymentSystem;

    private LocalDate date;

}
