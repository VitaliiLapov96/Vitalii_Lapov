package com.epam.spring.homework4.payments.controller;

import com.epam.spring.homework4.payments.controller.dto.CreditCardDto;
import com.epam.spring.homework4.payments.service.CreditCardService;
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
public class CreditCardController {

    private final CreditCardService creditCardService;

    @ApiOperation("Create credit card")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/credit-card")
    public CreditCardDto createCreditCard(@RequestBody @Valid CreditCardDto creditCardDto) {
        return creditCardService.createCreditCard(creditCardDto);
    }

    @ApiOperation("Get credit card by id")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/credit-card/{creditCardId}")
    public CreditCardDto getCreditCard(@PathVariable @Min(1) Long creditCardId) {
        return creditCardService.getCreditCard(creditCardId);
    }

    @ApiOperation("Update credit card by id")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/credit-card/{creditCardId}")
    public CreditCardDto updateCreditCard(@PathVariable @Min(1) Long creditCardId,
                                       @RequestBody @Valid CreditCardDto creditCardDto) {
        return creditCardService.updateCreditCard(creditCardId, creditCardDto);
    }

    @ApiOperation("Delete credit card by id")
    @DeleteMapping("/credit-card/{creditCardId}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable @Min(1) Long creditCardId) {
        creditCardService.deleteCreditCard(creditCardId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation("Get credit cards")
    @GetMapping("/credit-card")
    public List<CreditCardDto> listCreditCards() {
        return creditCardService.listCreditCards();
    }

}
