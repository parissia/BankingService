package com.bank.account.controller;

import com.bank.account.api.CustomerApi;
import com.bank.account.constants.ResponseCodeDescription;
import com.bank.account.model.AccountServiceResponse;
import com.bank.account.model.BankResponse;
import com.bank.account.model.Customer;
import com.bank.account.model.CustomerServiceResponse;
import com.bank.account.service.AccountService;
import com.bank.account.service.CustomerService;
import com.bank.account.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class BankCustomerController implements CustomerApi {

    private final CustomerService customerService;

    private final AccountService accountService;

    public BankCustomerController(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @Override
    public ResponseEntity<BankResponse> createNewBankAccount(Model model, @RequestBody Customer customer) {
        BankResponse response = new BankResponse();
        AccountServiceResponse accountServiceResponse;

        if (customer.getCustomerId() == null)
            handleError(model, ResponseCodeDescription.ERROR_CUSTOMER.getCode(), ResponseCodeDescription.ERROR_CUSTOMER.getDescription());

        accountServiceResponse = accountService.newAccount(customer.getCustomerId());
        response.setAccount(accountServiceResponse.getAccount());

        if (accountServiceResponse.getServiceResponse().getCode() != ResponseCodeDescription.SUCCESS_ACCOUNT.getCode())
            handleError(model, accountServiceResponse.getServiceResponse().getCode(), accountServiceResponse.getServiceResponse().getDescription());
        else {
            log.info("Account created successfully!");
        }
        return (ResponseEntity<BankResponse>) ResponseUtil.preperEntityForOk(accountServiceResponse, response);
    }


    @Override
    public String showCustomerInformation(Model model, @RequestParam(value = "id") Long id) {
        Customer customer = null;
        CustomerServiceResponse customerServiceResponse = new CustomerServiceResponse();

        if (id == null) handleError(model, "", "");

        customerServiceResponse = customerService.findCustomer(id);

        if (customerServiceResponse.getServiceResponse().equals(ResponseCodeDescription.SUCCESS_CUSTOMER))
        {
            customer = customerServiceResponse.getCustomer();
            Long currentBalance = accountService.getTotalBalance(id);
            model.addAttribute("customer", customer);
            model.addAttribute("balance", currentBalance);
            return "customer";
        }
        else
            return handleError(model, customerServiceResponse.getServiceResponse().getCode(), customerServiceResponse.getServiceResponse().getDescription());
    }

    @Override
    public String handleError(Model model, String code, String description) {
        model.addAttribute("errorCode", code);
        model.addAttribute("errorDescription", description);

        return "error";
    }


}
