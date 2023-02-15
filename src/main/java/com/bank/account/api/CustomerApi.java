package com.bank.account.api;

import com.bank.account.model.BankResponse;
import com.bank.account.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/v1/bank/customer")
public interface CustomerApi {

    @PostMapping(value = "account/new", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BankResponse> createNewBankAccount(Model model, Customer customer);

    @GetMapping(value = "show", produces = MediaType.APPLICATION_JSON_VALUE)
    String showCustomerInformation(Model model, @RequestParam(value = "id") Long id);

    @GetMapping("/api/v1/bank/error")
    public String handleError(Model model, String errorCode, String errorDescription);

}
