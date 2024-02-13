package com.shop.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shop.enums.BizStatusCode;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
//    @ExceptionHandler(Exception.class) 
//    public ResponseEntity<String> handleException(Exception e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Operation failed: " + e.getMessage());
//    }
//    
//
//    @ExceptionHandler(CustomException.class)
//    public ResponseEntity<String> handleCustomException(CustomException e) {
//        return ResponseEntity.status(e.getHttpStatus()).body(e.getHttpStatusAndMessage());
//    }
    
//	@ExceptionHandler(Exception.class) 
//	public ResponseEntity<String> handleException(Exception e) {
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Operation failed: " + e.getMessage());
//	}

//	@ExceptionHandler(CustomException.class)
//	public final ResponseEntity<Object> handleCustomException(CustomException ex, WebRequest request) {
//		int statusCode = ex.getHttpStatus();
//		ExceptionResponse exceptionResponse = new ExceptionResponse(
//	            new Date(),
//	            ex.getMessage(),
//	            request.getDescription(false), statusCode);
//	    return ResponseEntity.status(statusCode).body(exceptionResponse);
//	   
//	@ExceptionHandler(CustomException.class)
//	public final ResponseEntity<Object> handleCustomException(CustomException ex, WebRequest request) {
//	    int statusCode = ex.getHttpStatus();
//	    ExceptionResponse exceptionResponse = new ExceptionResponse(
//	            new Date(),
//	            ex.getMessage(),
//	            request.getDescription(false));
//	    return ResponseEntity.status(statusCode).body(exceptionResponse);
//	}
	@ExceptionHandler(CustomException.class)
	public final ResponseEntity<Object> handleCustomException(CustomException ex, WebRequest request) {
	    BizStatusCode bizStatusCode = ex.getBizStatusCode();
	    ExceptionResponse exceptionResponse = new ExceptionResponse(
	            new Date(),
	            bizStatusCode.getMessage(),
	            request.getDescription(false));


	    return ResponseEntity.status(Integer.parseInt(bizStatusCode.getCode())).body(exceptionResponse);
	}

}
