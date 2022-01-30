package com.epam.spring.homework5.payments.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Double creditLimit;

    private Double balance;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String number;

    private String currency;

    private LocalDate date;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountFrom")
    private List<Payment> paymentsFrom;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountTo")
    private List<Payment> paymentsTo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
    private List<CreditCard> creditCards;

}
