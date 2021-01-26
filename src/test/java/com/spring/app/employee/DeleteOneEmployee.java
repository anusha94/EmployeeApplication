package com.spring.app.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

import com.spring.app.employee.exceptions.BusinessException;
import com.spring.app.employee.pojos.responses.ApiResponse;
import com.spring.app.employee.utils.TestUtil;
import static com.spring.app.employee.utils.Constants.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeleteOneEmployee {

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
	public void deleteExistingEmployee() {
		String name = "Anusha";
		Map<String, String> vars = new HashMap<>();
		vars.put("name", name);
		this.restTemplate.delete(API_URL, ApiResponse.class, vars);
		ApiResponse response = this.getApiResponse(name);
		assertEquals(EMPLOYEE_NOT_FOUND, response.getErrorCode());

	}
	
	@Test
	public void deleteMissingEmployee() {
		String name = "Anusha Hegde";
		Map<String, String> vars = new HashMap<>();
		vars.put("name", name);
		this.restTemplate.delete(API_URL, ApiResponse.class, vars);
		ApiResponse response = this.getApiResponse(name);
		assertThrows(BusinessException.class, null);
		assertEquals(EMPLOYEE_NOT_FOUND, response.getErrorCode());
	}
}
