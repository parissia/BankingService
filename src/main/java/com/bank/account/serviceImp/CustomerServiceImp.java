package com.bank.account.serviceImp;

import com.bank.account.constants.ResponseCodeDescription;
import com.bank.account.data.AccountRepository;
import com.bank.account.data.CustomerRepository;
import com.bank.account.model.*;
import com.bank.account.service.CustomerService;
import com.bank.account.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private final TransactionService transactionService;

    private final CustomerRepository customerRepository;

    private final AccountRepository accountRepository;

    public CustomerServiceImp(CustomerRepository customerRepository, AccountRepository accountRepository, TransactionService transactionService) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(c -> customers.add(c));
        return customers;
    }

    @Override
    public CustomerServiceResponse findCustomer(Long customerId) {
        CustomerServiceResponse customerServiceResponse = new CustomerServiceResponse();

        Optional<Customer> customer = customerRepository.findById(customerId);

        if (!customer.isPresent()) {
            customerServiceResponse.setServiceResponse(ResponseCodeDescription.ERROR_CUSTOMER);
        } else {
            customerServiceResponse.setCustomer(customer.get());
            customerServiceResponse.setServiceResponse(ResponseCodeDescription.SUCCESS_CUSTOMER);
        }

        return customerServiceResponse;
    }

    @Override
    public TransactionServiceResponse checkInitialCredit(Account newAccount, Long initialCredit) {
        TransactionServiceResponse transactionServiceResponse = new TransactionServiceResponse();

        if (initialCredit <= 0) {
             transactionServiceResponse.setServiceResponse(ResponseCodeDescription.ERROR_TRANSACTION);
             return transactionServiceResponse;
        }

        return transactionService.newTransaction(newAccount);
    }
}
