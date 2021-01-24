package com.spring.app.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public Optional<Employee> getEmployee(String name) {
		return employeeRepository.findById(name);
	}
	
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	public void addEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
	
	public void deleteEmployee(String name) {
		employeeRepository.deleteById(name);
	}
}
