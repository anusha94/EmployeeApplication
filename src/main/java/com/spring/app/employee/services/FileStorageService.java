package com.spring.app.employee.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.app.employee.exceptions.BusinessException;
import com.spring.app.employee.pojos.Task;
import com.spring.app.employee.repository.TaskRepository;


import static com.spring.app.employee.utils.Constants.*;

@Service
public class FileStorageService {


	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TaskProcessingService taskProcessingService;

	public Task store(MultipartFile file) throws BusinessException {

		if (file.isEmpty()) {
			throw new BusinessException("Failed to store empty file ", 400);
		}
		String taskId = UUID.randomUUID().toString();
		Task task = new Task(taskId, PROCESSING);
		Task savedTask = taskRepository.save(task);
		taskProcessingService.processTask(savedTask, file);
		return savedTask;

	}

}
