package com.shop.exception;

import com.shop.enums.BizStatusCode; 
import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
	

//	private final HttpStatus httpStatus;
//	
//	public CustomException(String message, HttpStatus httpStatus) { 
//		super(message);
//		this.httpStatus = httpStatus;
//	}
//	
//	public int getHttpStatus() {
//		//return httpStatus.NOT_FOUND.value();
//		return httpStatus.value();
//	} 
//	
	private final BizStatusCode bizStatusCode;
	
	public CustomException(String message, BizStatusCode bizStatusCode) {
		super(message);
		this.bizStatusCode = bizStatusCode;
	}

	public BizStatusCode getBizStatusCode() {
		return this.bizStatusCode;
	}
}
