package com.spring.app.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/employees")
	public @ResponseBody List<Employee> getAllEmployees() {
		return employeeService.getAllEmplloyees();
	}
	
	@RequestMapping("/employees/{name}")
	public Optional<Employee> getEmployee(@PathVariable String name) {
		return employeeService.getEmployee(name);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/employees/{name}")
	public void updateEmployee(@PathVariable String name, @RequestBody Employee employee) {
		employeeService.updateEmployee(employee);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/employees")
	public void addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/employees/{name}")
	public void deleteEmployee(@PathVariable String name) {
		employeeService.deleteEmployee(name);
	}
}
