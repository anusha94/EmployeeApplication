package com.spring.app.employee.services;

import java.util.List;

import com.spring.app.employee.pojos.Employee;

public interface EmployeeService {
	
	List<Employee> findPaginated(int pageNo, int pageSize);
}
