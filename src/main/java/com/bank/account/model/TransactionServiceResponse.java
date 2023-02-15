package com.bank.account.model;

import com.bank.account.constants.ResponseCode;

public class TransactionServiceResponse implements ServiceResponse{

    private Transaction transaction;
    private ResponseCode serviceResponse;

    @Override
    public ResponseCode getServiceResponse() {
        return serviceResponse;
    }

    public void setServiceResponse(ResponseCode serviceResponse) {
        this.serviceResponse = serviceResponse;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
