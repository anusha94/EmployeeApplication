package com.spring.app.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.app.employee.pojos.Employee;
import com.spring.app.employee.pojos.responses.ApiResponse;
import com.spring.app.employee.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetAllEmployeesTest {
	
	private final String API_URL = "/employees";
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private TestUtil testUtil;
	
	private ApiResponse getApiResponse() {
		ResponseEntity<ApiResponse> responseEntity = this.restTemplate.getForEntity(API_URL, ApiResponse.class);
		return responseEntity.getBody();
	}

	@BeforeEach
	public void setUp() {
		this.testUtil.deleteAllEmployees();
	}
	
	@Test
	public void testEmptyResponse() {
		ApiResponse response = this.getApiResponse();
		
		ObjectMapper mapper = new ObjectMapper();
		Employee[] employees =  mapper.convertValue(response.getResult(), Employee[].class);
		
		assertEquals(0, employees.length);
	}
	
	@Test
	public void testEmployeeResponse() {
		this.testUtil.addEmployees();
		ApiResponse response = this.getApiResponse();
		ObjectMapper mapper = new ObjectMapper();
		Employee[] employees =  mapper.convertValue(response.getResult(), Employee[].class);
		assertEquals(5, employees.length);
	}
	
}
