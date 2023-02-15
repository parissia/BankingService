package com.bank.account.service;
import com.bank.account.model.*;

import java.util.List;

public interface CustomerService {

    public List<Customer> getCustomers();
    public CustomerServiceResponse findCustomer(Long CustomerId);

    public TransactionServiceResponse checkInitialCredit(Account newAccount, Long initialCredit);
}
