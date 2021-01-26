package com.spring.app.employee;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.spring.app.employee.services.EmployeeServiceImpl;
import com.spring.app.employee.services.FileStorageService;
import com.spring.app.employee.services.TaskProcessingService;

import lombok.extern.slf4j.Slf4j;

import static com.spring.app.employee.utils.Constants.*;

@RestController
@Slf4j
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	@Autowired
	private FileStorageService fileStorageService;
	
	@Autowired
	private TaskProcessingService taskProcessingService;
	
	@GetMapping("/employees")
	public ResponseEntity<ApiResponse> getAllEmployees() {
		log.info("get all employees");
		List<Employee> employees = employeeServiceImpl.getAllEmplloyees();
		ApiResponse response = new ApiResponse(SUCCESS, employees);
		log.info(response.toString());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/employees/{name}")
	public ResponseEntity<ApiResponse> getEmployee(@PathVariable String name) throws BusinessException {
		log.info(name);
		Employee employee = employeeServiceImpl.getEmployee(name);
		ApiResponse response = new ApiResponse(SUCCESS, employee);
		log.info(response.getResult().toString());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping("/employees/{name}")
	public ResponseEntity<ApiResponse> updateEmployee(@PathVariable String name, @Valid @RequestBody EmployeeRequest employeeRequest) throws BusinessException {
		Employee employee = employeeServiceImpl.updateEmployee(name, employeeRequest);
		ApiResponse response = new ApiResponse(SUCCESS, employee);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping("/employees")
	public ResponseEntity<ApiResponse> addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) throws BusinessException {
		Employee emp = employeeServiceImpl.addEmployee(employeeRequest);
		ApiResponse response = new ApiResponse(SUCCESS, emp);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/employees/{name}")
	public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable String name) {
		employeeServiceImpl.deleteEmployee(name);
		ApiResponse response = new ApiResponse(SUCCESS);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping("/employees/upload")
	public ResponseEntity<ApiResponse> handleFileUpload(@RequestParam("file") MultipartFile file) throws BusinessException, InterruptedException {
		Task task = fileStorageService.store(file);
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
