package com.epam.spring.homework3.payments.controller;

import com.epam.spring.homework3.payments.controller.dto.CreditCardDto;
import com.epam.spring.homework3.payments.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/credit-card")
    public CreditCardDto createCreditCard(@RequestBody CreditCardDto creditCardDto) {
        return creditCardService.createCreditCard(creditCardDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/credit-card/{creditCardId}")
    public CreditCardDto getCreditCard(@PathVariable Long creditCardId) {
        return creditCardService.getCreditCard(creditCardId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/credit-card/{creditCardId}")
    public CreditCardDto updateCreditCard(@PathVariable Long creditCardId,
                                       @RequestBody CreditCardDto creditCardDto) {
        return creditCardService.updateCreditCard(creditCardId, creditCardDto);
    }

    @DeleteMapping("/credit-card/{creditCardId}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable Long creditCardId) {
        creditCardService.deleteCreditCard(creditCardId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/credit-card")
    public List<CreditCardDto> listCreditCards() {
        return creditCardService.listCreditCards();
    }

}
