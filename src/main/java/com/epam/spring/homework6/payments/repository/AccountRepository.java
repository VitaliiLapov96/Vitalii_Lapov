package com.epam.spring.homework6.payments.repository;

import com.epam.spring.homework6.payments.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
