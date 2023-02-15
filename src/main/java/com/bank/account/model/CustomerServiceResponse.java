package com.bank.account.model;

import com.bank.account.constants.ResponseCode;

public class CustomerServiceResponse implements ServiceResponse{

    private Customer customer;
    private ResponseCode serviceResponse;

    @Override
    public ResponseCode getServiceResponse() {
        return serviceResponse;
    }

    public void setServiceResponse(ResponseCode serviceResponse) {
        this.serviceResponse = serviceResponse;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
