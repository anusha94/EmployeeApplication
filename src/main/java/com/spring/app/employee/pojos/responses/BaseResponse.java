package com.spring.app.employee.pojos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {

	private String status;
	private Integer errorCode;
	private String error;
	
	public BaseResponse(String status) {
		this.status = status;
	}
	
	public BaseResponse(String status, Integer errorCode, String error) {
		this.status = status;
		this.errorCode = errorCode;
		this.error = error;
	}
}
