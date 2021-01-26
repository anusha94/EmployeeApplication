package com.spring.app.employee;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.spring.app.employee.utils.TestUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeFileUploadTest {

	private final String API_URL = "/api/employees/upload";

	
	@Autowired
	private TestUtil testUtil;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@BeforeEach
	public void setUp() {
		this.testUtil.deleteAllEmployees();
	}
	
	@Test
	public void whenFileUploaded_thenVerifyStatus() 
	  throws Exception {
	    MockMultipartFile file 
	      = new MockMultipartFile(
	        "file", 
	        "test.txt", 
	        MediaType.TEXT_PLAIN_VALUE, 
	        "Anusha,26\n Akash,27".getBytes()
	      );

	    MockMvc mockMvc 
	      = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	    mockMvc.perform(multipart(API_URL).file(file))
	      .andExpect(status().isOk());
	}

}
