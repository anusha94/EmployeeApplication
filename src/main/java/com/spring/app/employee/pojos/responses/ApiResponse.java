package com.spring.app.employee.pojos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
