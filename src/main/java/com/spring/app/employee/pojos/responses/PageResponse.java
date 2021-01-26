package com.spring.app.employee.pojos.responses;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> extends BaseResponse{

	private Page<T> result;
	
	public PageResponse(String status) {
		super(status);
	}
	
	public PageResponse(String status, Page<T> result) {
		super(status);
		this.result = result;
	}
	
	public PageResponse(String status, Integer errorCode, String error) {
		super(status, errorCode, error);
	}
	
	public PageResponse(String status, Integer errorCode, String error, Page<T> result) {
		super(status, errorCode, error);
		this.result = result;
	}
	
}
