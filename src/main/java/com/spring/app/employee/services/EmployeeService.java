package com.spring.app.employee.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.app.employee.exceptions.BusinessException;
import com.spring.app.employee.pojos.Employee;
import com.spring.app.employee.pojos.requests.EmployeeRequest;
import com.spring.app.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmplloyees() {
		List<Employee> employees = new ArrayList<>();
		employeeRepository.findAll()
				.forEach(employees::add);;
		return employees;
	}
	
	public Employee getEmployee(String name) throws BusinessException {
		Employee employee = employeeRepository.findById(name)
				.orElseThrow((() -> new BusinessException("Employee not found", 400)));
		return employee;
	}
	
	public Employee updateEmployee(EmployeeRequest employeeRequest) {
		Employee employee = new Employee(employeeRequest.getName(), employeeRequest.getAge());
		return employeeRepository.save(employee);
	}
	
	public Employee addEmployee(EmployeeRequest employeeRequest) throws BusinessException {
		this.validateAge(employeeRequest.getAge());
		Employee employee = new Employee(employeeRequest.getName(), employeeRequest.getAge());	
		return employeeRepository.save(employee);
	}
	
	public void deleteEmployee(String name) {
		employeeRepository.deleteById(name);
	}
	
	private void validateAge(Integer age) throws BusinessException {
		if(age < 0) {
			throw new BusinessException("Invalid Age range", 400);
		}
	}
}
