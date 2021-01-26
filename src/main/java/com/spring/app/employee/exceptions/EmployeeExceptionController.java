package com.spring.app.employee.exceptions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.app.employee.pojos.responses.ApiResponse;

import lombok.extern.slf4j.Slf4j;

import static com.spring.app.employee.utils.Constants.*;

@ControllerAdvice
@Slf4j
public class EmployeeExceptionController extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
		ex.printStackTrace();
		log.error("Handle Business Exception - ", ex.getMessage());
		ApiResponse response = new ApiResponse(FAILURE, ex.getCode(), ex.getMessage());
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("Handle HTTP message not readable exception - " + e.getMessage(), e);
		ApiResponse response = new ApiResponse(FAILURE, INVALID_JSON, INVALID_JSON_STR);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("Handle method argument not valid exception - ", e.getMessage(), e);
		ApiResponse response = new ApiResponse(FAILURE, INVALID_JSON, INVALID_JSON_STR);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}
