package com.spring.app.employee.utils;

public class Constants {
	//Response Constants
	public static final String SUCCESS = "success";
	public static final String FAILURE = "failure";
	
	
	// Status Codes
	public static final Integer INVALID_JSON = 1001;
	public static final Integer TASK_NOT_FOUND = 2001;
	public static final Integer EMPLOYEE_NOT_FOUND = 2002;
	public static final Integer INVALID_AGE_RANGE = 2003;
	public static final Integer FILE_PROCESSING_ERROR = 2004;
	public static final Integer FILE_EMPTY_ERROR = 2005;
	public static final Integer EMPLOYEE_ALREADY_EXISTS = 2006;
	
	// Status string messages
	public static final String INVALID_JSON_STR = "invalid json";
	public static final String TASK_NOT_FOUND_STR = "task not found";
	public static final String EMPLOYEE_NOT_FOUND_STR = "employee not found";
	public static final String INVALID_AGE_RANGE_STR = "invalid age";
	public static final String FILE_PROCESSING_ERROR_STR = "error when processing the file";
	public static final String FILE_EMPTY_ERROR_STR = "uploaded file cannot be empty";
	public static final String EMPLOYEE_ALREADY_EXISTS_STR = "employee already exists";
	
	// Task constants
	public static final String PROCESSING = "processing";
	public static final String COMPLETED = "completed";
	
	public static final String FILE_UPLOAD_DELIMITER = " ";
}
