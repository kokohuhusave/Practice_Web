package com.shop.exception;

import java.util.Date;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.shop.config.MessageService;
import com.shop.enums.BizStatusCode;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	private final MessageService messageService;
	
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
	@ExceptionHandler(CustomException.class) // CustomException 발생할때 호출
	public final ResponseEntity<?> handleCustomException(CustomException ex, WebRequest request) {
	    BizStatusCode bizStatusCode = ex.getBizStatusCode(); // 예외에서 비즈니스 상태 코드를 객체로 가져음
	    String messageKey = bizStatusCode.getMessage();  // 비즈니스 상태 코드 객체로부터 메시지 키를 추출
	    return ResponseEntity // 여기 제네릭 적용 ?? 한번 알아볼것 body 다바꿔야 할수있음
	    		.status(Integer.parseInt(bizStatusCode.getCode())) //HTTP상태코드 설정
	    		.body(ExceptionResponse.builder()  // 빌더패턴 
	    				.timestamp(new Date())
	    				.message(messageService.getMessage(messageKey))
	    				.details(request.getDescription(false))
	    				);
	}
	
	// HttpMediaTypeNotAcceptable Exception
	@Override
	public ResponseEntity<Object> handleHttpMediaTypeNotSupported(
	        HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

	    ExceptionResponse exceptionResponse = new ExceptionResponse(
	            new Date(),
	            "허용되지 않은 미디어 타입입니다.",
	            "허용된 미디어 타입: " + ex.getSupportedMediaTypes().toString());

	    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(exceptionResponse);
	}
	
	
//	
}
