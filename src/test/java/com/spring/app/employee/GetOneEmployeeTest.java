package com.spring.app.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

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

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetOneEmployeeTest {
	
	private final String API_URL = "/employees/{name}";
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private TestUtil testUtil;
	
	private ApiResponse getApiResponse(String name) {
		Map<String, String> vars = new HashMap<>();
		vars.put("name", name);
		ResponseEntity<ApiResponse> responseEntity = this.restTemplate.getForEntity(API_URL, ApiResponse.class, vars);
		return responseEntity.getBody();
	}
	
	@BeforeEach
	public void setUp() {
		this.testUtil.deleteAllEmployees();
		this.testUtil.addEmployees();
	}
	
	@Test
	public void getOneEmployee() {
		String name = "Anusha";
		Integer age = 26;
		ApiResponse response = this.getApiResponse(name);
		ObjectMapper mapper = new ObjectMapper();
		Employee employee =  mapper.convertValue(response.getResult(), Employee.class);
		
		assertEquals(name, employee.getName());
		assertEquals(age, employee.getAge());
	}

}
