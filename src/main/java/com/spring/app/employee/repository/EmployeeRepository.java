package com.spring.app.employee.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.app.employee.pojos.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, String>{
	
	Page<Employee> findAll(Pageable pageable);

}
