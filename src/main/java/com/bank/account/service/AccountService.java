package com.bank.account.service;

import com.bank.account.model.Account;
import com.bank.account.model.AccountServiceResponse;

import java.util.List;

public interface AccountService {
    public Account findOne(Long accountId);
    public AccountServiceResponse newAccount(Long customerId);
    public Long getTotalBalance(Long customerId);
    public List<Account> getAll(Long customerId);
}
