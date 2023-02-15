package com.bank.account.serviceImp;

import com.bank.account.constants.ResponseCodeDescription;
import com.bank.account.data.AccountRepository;
import com.bank.account.model.*;
import com.bank.account.service.AccountService;
import com.bank.account.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private final CustomerService customerService;

    private final AccountRepository accountRepository;

    public AccountServiceImp(AccountRepository accountRepository, CustomerService customerService) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
    }

    @Override
    public Account findOne(Long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        return account.isPresent() ? account.get() : null;
    }

    @Override
    public AccountServiceResponse newAccount(Long customerId) {
        AccountServiceResponse accountServiceResponse = new AccountServiceResponse();
        CustomerServiceResponse customerServiceResponse;
        TransactionServiceResponse transactionServiceResponse;

        Account newAccount = null;

        customerServiceResponse = customerService.findCustomer(customerId);
        if (customerServiceResponse.getServiceResponse().equals(ResponseCodeDescription.ERROR_CUSTOMER)) {
            log.error(customerServiceResponse.getServiceResponse().getDescription());
            accountServiceResponse.setServiceResponse(ResponseCodeDescription.ERROR_CUSTOMER);
            return  accountServiceResponse;
        }

        Customer customer = customerServiceResponse.getCustomer();

        try {
            newAccount = new Account();
            newAccount.setCustomerId(customerId);
            customer.getAccounts().add(newAccount);
            accountRepository.save(newAccount);

            accountServiceResponse.setAccount(newAccount);
            accountServiceResponse.setServiceResponse(ResponseCodeDescription.SUCCESS_ACCOUNT);

            transactionServiceResponse = customerService.checkInitialCredit(newAccount, customer.getInitialCredit());
            if (transactionServiceResponse.getServiceResponse().getCode().equals(ResponseCodeDescription.ERROR_TRANSACTION.getCode())) {
                log.error(transactionServiceResponse.getServiceResponse().getDescription());
                accountServiceResponse.setServiceResponse(ResponseCodeDescription.ERROR_TRANSACTION);
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
            accountServiceResponse.setServiceResponse(ResponseCodeDescription.ERROR_ACCOUNT);

        }


        return accountServiceResponse;
    }

    @Override
    public Long getTotalBalance(Long customerId) {
        return accountRepository.findBalance(customerId);
    }

    @Override
    public List<Account> getAll(Long customerId) {
        List<Account> allAccounts = new ArrayList<>();
        List<Long> accounts = accountRepository.findAccounts(customerId);
        accounts.forEach(a -> {
            Account account = findOne(a);
            allAccounts.add(account);
        });
        return allAccounts;
    }

}
