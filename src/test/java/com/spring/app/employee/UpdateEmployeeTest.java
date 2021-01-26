package com.spring.app.employee;

import static com.spring.app.employee.utils.Constants.EMPLOYEE_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.app.employee.exceptions.BusinessException;
import com.spring.app.employee.pojos.Employee;
import com.spring.app.employee.pojos.requests.EmployeeRequest;
import com.spring.app.employee.pojos.responses.ApiResponse;
import com.spring.app.employee.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateEmployeeTest {
	
	private final String API_URL = "/employees";
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private TestUtil testUtil;
	
	private ApiResponse getApiResponse(EmployeeRequest request) {
		ResponseEntity<ApiResponse> responseEntity = this.restTemplate.postForEntity(API_URL, request, ApiResponse.class);
		return responseEntity.getBody();
	}
	
	@BeforeEach
	public void setUp() {
		this.testUtil.deleteAllEmployees();
		this.testUtil.addEmployees();
	}
	
	@Test
	public void updateExistingEmployee() {
		String name = "Anusha";
		Integer age = 30;
		EmployeeRequest request = new EmployeeRequest();
		request.setName(name);
		request.setAge(age);
		ApiResponse response = this.getApiResponse(request);
		ObjectMapper mapper = new ObjectMapper();
		Employee employee =  mapper.convertValue(response.getResult(), Employee.class);
		
		assertEquals(name, employee.getName());
		assertEquals(age, employee.getAge());
	}
	
	@Test
	public void updateMissingEmployee() {
		String name = "Anusha Hegde";
		Integer age = 30;
		EmployeeRequest request = new EmployeeRequest();
		request.setName(name);
		request.setAge(age);
//		Assert.Throws<BusinessException>(() => user.MakeUserActive());
//		Assert.Throws<BusinessException>(() => this.getApiResponse(request));
	}
}
