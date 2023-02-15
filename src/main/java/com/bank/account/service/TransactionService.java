package com.bank.account.service;

import com.bank.account.model.Account;
import com.bank.account.model.Transaction;
import com.bank.account.model.TransactionServiceResponse;

import java.util.List;

public interface TransactionService {

    public List<Transaction> getAll(Account account);
    public TransactionServiceResponse newTransaction(Account account);
//    public Transaction findOne(Long transactionId) throws TransactionException;
}
