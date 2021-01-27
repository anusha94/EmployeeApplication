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
import com.spring.app.employee.controller.EmployeeController;
import com.spring.app.employee.exceptions.BusinessException;
import com.spring.app.employee.pojos.Employee;
import com.spring.app.employee.pojos.requests.EmployeeRequest;
import com.spring.app.employee.pojos.responses.ApiResponse;
import com.spring.app.employee.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeUpdateTest {
	
	@Autowired
	private TestUtil testUtil;
	
	@Autowired
	private EmployeeController employeeController;
	
	private ApiResponse getApiResponse(EmployeeRequest request) throws BusinessException {
		ResponseEntity<ApiResponse> responseEntity = this.employeeController.updateEmployee(request);
		return responseEntity.getBody();
	}
	
	@BeforeEach
	public void setUp() {
		this.testUtil.deleteAllEmployees();
		this.testUtil.addEmployees();
	}
	
	@Test
	public void updateExistingEmployee() throws BusinessException {
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

}
