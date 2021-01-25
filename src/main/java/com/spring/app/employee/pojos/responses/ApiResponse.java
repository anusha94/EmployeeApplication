package com.spring.app.employee.pojos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse extends BaseResponse{
	private Object result;
	
	public ApiResponse(String status) {
		super(status);
	}
	
	public ApiResponse(String status, Object result) {
		super(status);
		this.result = result;
	}
	
	public ApiResponse(String status, Integer errorCode, String error) {
		super(status, errorCode, error);
	}
	
	public ApiResponse(String status, Integer errorCode, String error, Object result) {
		super(status, errorCode, error);
		this.result = result;
	}
}
