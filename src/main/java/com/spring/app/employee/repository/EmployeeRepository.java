package com.spring.app.employee.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.app.employee.pojos.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, String>{

}
