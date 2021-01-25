package com.spring.app.employee;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.app.employee.exceptions.BusinessException;
import com.spring.app.employee.pojos.Employee;
import com.spring.app.employee.pojos.requests.EmployeeRequest;
import com.spring.app.employee.pojos.responses.ApiResponse;
import com.spring.app.employee.services.EmployeeService;

import static com.spring.app.employee.utils.Constants.*;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/employees")
	public ResponseEntity<ApiResponse> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmplloyees();
		ApiResponse response = new ApiResponse(SUCCESS, employees);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@RequestMapping("/employees/{name}")
	public ResponseEntity<ApiResponse> getEmployee(@PathVariable String name) throws BusinessException {
		Employee employee = employeeService.getEmployee(name);
		ApiResponse response = new ApiResponse(SUCCESS, employee);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/employees/{name}")
	public ResponseEntity<ApiResponse> updateEmployee(@PathVariable String name, @Valid @RequestBody EmployeeRequest employeeRequest) {
		Employee emp = employeeService.updateEmployee(employeeRequest);
		ApiResponse response = new ApiResponse(SUCCESS, emp);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/employees")
	public ResponseEntity<ApiResponse> addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) throws BusinessException {
		Employee emp = employeeService.addEmployee(employeeRequest);
		ApiResponse response = new ApiResponse(SUCCESS, emp);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/employees/{name}")
	public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable String name) {
		employeeService.deleteEmployee(name);
		ApiResponse response = new ApiResponse(SUCCESS);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
