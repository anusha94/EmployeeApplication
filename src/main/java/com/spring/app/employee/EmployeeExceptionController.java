package com.spring.app.employee;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionController {
	private static final Logger logger = LoggerFactory.getLogger(EmployeeExceptionController.class);
	
	@ExceptionHandler(value={InvalidInputException.class})
	public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {
		logger.error("InvalidInputException ", ex.getMessage());
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value={BusinessException.class})
	public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
		logger.error("BusinessException ", ex.getMessage());
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value={EmployeeNotFoundException.class})
	public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
		logger.error("EmployeeNotFoundException ", ex.getMessage());
		return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value={Exception.class})
	public ResponseEntity<Object> handleException(Exception ex) {
		logger.error("Exception: ",ex.getMessage());
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}	

}

class InvalidInputException extends RuntimeException {
	private static final long serialVersionUID = 1L;
}

class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
}

class EmployeeNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
}