package com.bank.account.model;

public class AccountStatus implements Status {
    private String statusCode;

    private String statusDescription;

    @Override
    public String getStatusCode() {
        return statusCode;
    }

    @Override
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getStatusDescription() {
        return statusDescription;
    }

    @Override
    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }
}
