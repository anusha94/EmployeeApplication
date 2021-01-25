package com.spring.app.employee.pojos;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Employee {

	@Id
	String name;
	Integer age;
	
	public Employee(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
	
}
