package com.spring.app.employee.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.app.employee.exceptions.BusinessException;
import com.spring.app.employee.pojos.Employee;
import com.spring.app.employee.pojos.Task;
import com.spring.app.employee.repository.EmployeeRepository;
import com.spring.app.employee.repository.TaskRepository;

import static com.spring.app.employee.utils.Constants.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaskProcessingService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired EmployeeRepository employeeRepository;

	@Async
	public void processTask(Task task, MultipartFile file) throws BusinessException {
		List<Employee> employees = new ArrayList<>();
		try {
			InputStream inputStream = file.getInputStream();
			new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).lines()
					.forEach((String line) -> {
						try {
							this.handleLine(line, employees);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
			employeeRepository.saveAll(employees);
			this.updateTask(task, COMPLETED);
		} catch (

		Exception e) {
			log.error(e.getMessage());
			throw new BusinessException("Failed to store file ", 400);
		}
	}
	
	public void updateTask(Task task, String status) {
		task.setStatus(status);
		taskRepository.save(task);
	}


	public void handleLine(String line, List<Employee> employees) throws InterruptedException {
		Thread.sleep(5000);
		log.info(line);
		String parts[] = line.split (" ");
		Integer age = Integer.parseInt(parts[parts.length-1]);
		String nameArr[] = Arrays.copyOf(parts, parts.length-1);
		String name = StringUtils.join(nameArr);
		Employee employee = new Employee(name, age);
		employees.add(employee);
	}
	
	public Task getTaskStatus(String id) throws BusinessException {
		Task task = taskRepository.findById(id)
				.orElseThrow((() -> new BusinessException("Employee not found", 400)));
		return task;
	}
}
