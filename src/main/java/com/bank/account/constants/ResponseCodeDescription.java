 package com.bank.account.constants;

public enum ResponseCodeDescription implements ResponseCode {
	
	SUCCESS_ACCOUNT("1000","Account successfully created."),
	SUCCESS_CUSTOMER("1001","The customer was found successfully."),
	SUCCESS_TRANSACTION("1002","The transaction has been created successfully."),

	ERROR_ACCOUNT("1003","There was an issue processing the account."),
	ERROR_CUSTOMER("1004","Customer not found."),
	ERROR_TRANSACTION("1005","Unable to create transaction."),
	;
	
	private final String code;

	private final String description;

	ResponseCodeDescription(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String getDescription() {
		return description;
	}

}
