package com.spring.app.employee.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.spring.app.employee.pojos.Employee;
import com.spring.app.employee.repository.EmployeeRepository;

@Component
public class TestUtil {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public void addEmployees() {
		Employee e1 = new Employee("Anusha", 26);
		Employee e2 = new Employee("Akash", 27);
		Employee e3 = new Employee("Meghana", 31);
		Employee e4 = new Employee("Varsha", 16);
		Employee e5 = new Employee("Vanya", 10);
		List<Employee> employees = new ArrayList<>();
		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
		employees.add(e4);
		employees.add(e5);
		
		this.employeeRepository.saveAll(employees);
	}
	
	public void deleteAllEmployees() {
		this.employeeRepository.deleteAll();
	}
}
