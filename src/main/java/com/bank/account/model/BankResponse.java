package com.bank.account.model;

public class BankResponse implements Response{

    private AccountStatus status = new AccountStatus();
    private Account account;

    @Override
    public Status getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
