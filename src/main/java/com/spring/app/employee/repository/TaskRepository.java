package com.spring.app.employee.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.app.employee.pojos.Task;

public interface TaskRepository extends CrudRepository<Task, String> {

}
