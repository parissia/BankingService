package com.bank.account.model;

import com.bank.account.constants.ResponseCode;

public class AccountServiceResponse implements ServiceResponse{

    private Account account;
    private ResponseCode serviceResponse;

    @Override
    public ResponseCode getServiceResponse() {
        return serviceResponse;
    }

    public void setServiceResponse(ResponseCode serviceResponse) {
        this.serviceResponse = serviceResponse;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
