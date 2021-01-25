package com.spring.app.employee.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2594435861446739750L;
	private Integer code;
	private Object object;
	
	public BusinessException(String message, Integer code, Object object) {
		super(message);
		this.code = code;
		this.object = object;
	}
	
	public BusinessException(String message, Integer code) {
		super(message);
		this.code = code;
	}
}
