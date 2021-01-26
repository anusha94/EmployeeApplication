package com.spring.app.employee.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.app.employee.pojos.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, String>{
	

}
