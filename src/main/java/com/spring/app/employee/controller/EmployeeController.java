package com.spring.app.employee.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.spring.app.employee.exceptions.BusinessException;
import com.spring.app.employee.pojos.Employee;
import com.spring.app.employee.pojos.Task;
import com.spring.app.employee.pojos.requests.EmployeeRequest;
import com.spring.app.employee.pojos.responses.ApiResponse;
import com.spring.app.employee.pojos.responses.PageResponse;
import com.spring.app.employee.services.EmployeeService;
import com.spring.app.employee.services.FileUploadService;
import com.spring.app.employee.services.TaskProcessingService;

import static com.spring.app.employee.utils.Constants.*;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private TaskProcessingService taskProcessingService;
	
	@GetMapping("/employees")
	public ResponseEntity<PageResponse<Employee>> getAllEmployees(Pageable pageable) {
		Page<Employee> employees = employeeService.getAllEmplloyees(pageable);
		PageResponse<Employee> response = new PageResponse<Employee>(SUCCESS, employees);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/employees/{name}")
	public ResponseEntity<ApiResponse> getEmployee(@PathVariable String name) throws BusinessException {
		Employee employee = employeeService.getEmployee(name);
		ApiResponse response = new ApiResponse(SUCCESS, employee);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping("/employees/{name}")
	public ResponseEntity<ApiResponse> updateEmployee(@PathVariable String name, @Valid @RequestBody EmployeeRequest employeeRequest) throws BusinessException {
		Employee emp = employeeService.updateEmployee(name, employeeRequest);
		ApiResponse response = new ApiResponse(SUCCESS, emp);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping("/employees")
	public ResponseEntity<ApiResponse> addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) throws BusinessException {
		Employee emp = employeeService.addEmployee(employeeRequest);
		ApiResponse response = new ApiResponse(SUCCESS, emp);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/employees/{name}")
	public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable String name) {
		employeeService.deleteEmployee(name);
		ApiResponse response = new ApiResponse(SUCCESS);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping("/employees")
	public ResponseEntity<ApiResponse> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam String action) throws BusinessException, InterruptedException {
		Task task = fileUploadService.store(action, file);
		ApiResponse response = new ApiResponse(SUCCESS, task);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/tasks/{id}")
	public ResponseEntity<ApiResponse> getTaskStatus(@PathVariable String id) throws BusinessException {
		Task task = taskProcessingService.getTaskStatus(id);
		ApiResponse response = new ApiResponse(SUCCESS, task);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}