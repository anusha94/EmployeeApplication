package com.spring.app.employee.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.app.employee.exceptions.BusinessException;
import com.spring.app.employee.pojos.Employee;
import com.spring.app.employee.pojos.requests.EmployeeRequest;
import com.spring.app.employee.repository.EmployeeRepository;

import static com.spring.app.employee.utils.Constants.*;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Page<Employee> getAllEmplloyees(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return employees;
	}
	
	public Employee getEmployee(String name) throws BusinessException {
		Employee employee = employeeRepository.findById(name)
				.orElseThrow((() -> new BusinessException(EMPLOYEE_NOT_FOUND_STR, EMPLOYEE_NOT_FOUND)));
		return employee;
	}
	
	public Employee updateEmployee(EmployeeRequest employeeRequest) throws BusinessException {
		this.getEmployee(employeeRequest.getName());
		Employee updatedEmployee = new Employee(employeeRequest.getName(), employeeRequest.getAge());
		return employeeRepository.save(updatedEmployee);
	}
	
	public Employee addEmployee(EmployeeRequest employeeRequest) throws BusinessException {
		this.validateAge(employeeRequest.getAge());
		this.validateIfEmployeeAlreadyExists(employeeRequest.getName());
		
		Employee employee = new Employee(employeeRequest.getName(), employeeRequest.getAge());
		return employeeRepository.save(employee);
	}
	
	public void deleteEmployee(String name) throws BusinessException {
		this.getEmployee(name);
		employeeRepository.deleteById(name);
	}
	
	private void validateAge(Integer age) throws BusinessException {
		if(age < 0) {
			throw new BusinessException(INVALID_AGE_RANGE_STR, INVALID_AGE_RANGE);
		}
	}
	
	private void validateIfEmployeeAlreadyExists(String name) throws BusinessException {
		Boolean employeeExists = employeeRepository.findById(name)
				.isPresent();
		if(employeeExists) {
			throw new BusinessException(EMPLOYEE_ALREADY_EXISTS_STR, EMPLOYEE_ALREADY_EXISTS);
		}
		
	}

}
