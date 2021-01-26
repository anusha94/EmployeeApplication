package com.spring.app.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.app.employee.controller.EmployeeController;
import com.spring.app.employee.pojos.Employee;
import com.spring.app.employee.pojos.responses.PageResponse;
import com.spring.app.employee.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetAllEmployeesTest {
	
	private final String API_URL = "/employees";
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private EmployeeController employeeController;
	
	@Autowired
	private TestUtil testUtil;
	
	private PageResponse<Employee> getPageResponse() {
		Pageable page = PageRequest.of(0, 5);
		ResponseEntity<PageResponse<Employee>> responseEntity = this.employeeController.getAllEmployees(page);
		return responseEntity.getBody();
	}

	@BeforeEach
	public void setUp() {
		this.testUtil.deleteAllEmployees();
	}
	
	@Test
	public void testEmptyResponse() throws JsonProcessingException {
		PageResponse<Employee> response = this.getPageResponse();
		assertEquals(0, response.getResult().getTotalElements());
	}
	
	@Test
	public void testEmployeeResponse() {
		this.testUtil.addEmployees();
		PageResponse<Employee> response = this.getPageResponse();
		assertEquals(5, response.getResult().getTotalElements());
	}
	
}
