package com.bank.account.serviceImp;

import com.bank.account.constants.ResponseCodeDescription;
import com.bank.account.data.TransactionRepository;
import com.bank.account.model.Account;
import com.bank.account.model.Transaction;
import com.bank.account.model.TransactionServiceResponse;
import com.bank.account.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TransactionServiceImp implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImp(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> getAll(Account account) {
        if (account == null) return null;
        return transactionRepository.findByAccountId(account.getAccountId());
    }

    @Override
    public TransactionServiceResponse newTransaction(Account account) {
        TransactionServiceResponse transactionServiceResponse = new TransactionServiceResponse();

        if (account == null) {
            transactionServiceResponse.setServiceResponse(ResponseCodeDescription.ERROR_ACCOUNT);
        }

        try {
            Transaction newTransaction = new Transaction();
            newTransaction.setAccountId(account.getAccountId());
            account.getTransactions().add(newTransaction);
            transactionRepository.save(newTransaction);

            transactionServiceResponse.setTransaction(newTransaction);
            transactionServiceResponse.setServiceResponse(ResponseCodeDescription.SUCCESS_TRANSACTION);
        } catch (Exception exception) {
            transactionServiceResponse.setServiceResponse(ResponseCodeDescription.ERROR_TRANSACTION);
            log.error(exception.getMessage());
        }

        return transactionServiceResponse;
    }
}
