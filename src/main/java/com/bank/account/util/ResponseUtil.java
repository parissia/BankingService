package com.bank.account.util;
import com.bank.account.constants.ResponseCode;
import com.bank.account.model.Response;
import com.bank.account.model.ServiceResponse;
import com.bank.account.model.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public interface ResponseUtil {

	public static ResponseEntity<?> preperEntityForException(ResponseCode responseCode, Response response) {
		Status status = response.getStatus();
		status.setStatusCode(responseCode.getCode());
		status.setStatusDescription(responseCode.getDescription());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	}

	public static ResponseEntity<?> preperEntityForBadRequest(ResponseCode responseCode, Response response) {
		Status status = response.getStatus();
		status.setStatusCode(responseCode.getCode());
		status.setStatusDescription(responseCode.getDescription());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	public static ResponseEntity<?> preperEntityForOk(ServiceResponse serviceResponse, Response response) {
		Status status = response.getStatus();
		if (serviceResponse != null) {
			status.setStatusCode(serviceResponse.getServiceResponse().getCode());
			status.setStatusDescription(serviceResponse.getServiceResponse().getDescription());
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
