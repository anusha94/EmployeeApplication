package com.spring.app.employee.pojos.requests;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private Integer age;
}
