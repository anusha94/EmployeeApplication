package com.spring.app.employee.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.spring.app.employee.exceptions.BusinessException;
import com.spring.app.employee.pojos.Employee;
import com.spring.app.employee.pojos.requests.EmployeeRequest;
import com.spring.app.employee.repository.EmployeeRepository;

import static com.spring.app.employee.utils.Constants.*;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmplloyees() {
//		List<Employee> employees = new ArrayList<>();
//		employeeRepository.findAll()
//				.forEach(employees::add);;
//		return employees;
		return this.findPaginated(0, 3);
	}
	
	public Employee getEmployee(String name) throws BusinessException {
		Employee employee = employeeRepository.findById(name)
				.orElseThrow((() -> new BusinessException(EMPLOYEE_NOT_FOUND_STR, EMPLOYEE_NOT_FOUND)));
		return employee;
	}
	
	public Employee updateEmployee(String name, EmployeeRequest employeeRequest) throws BusinessException {
		Employee employee = this.getEmployee(name);
		return employeeRepository.save(employee);
	}
	
	public Employee addEmployee(EmployeeRequest employeeRequest) throws BusinessException {
		this.validateAge(employeeRequest.getAge());
		Employee employee = new Employee(employeeRequest.getName(), employeeRequest.getAge());	
		return employeeRepository.save(employee);
	}
	
	public void deleteEmployee(String name) {
		employeeRepository.deleteById(name);
	}
	
	private void validateAge(Integer age) throws BusinessException {
		if(age < 0) {
			throw new BusinessException(INVALID_AGE_RANGE_STR, INVALID_AGE_RANGE);
		}
	}

	@Override
	public List<Employee> findPaginated(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
        List<Employee> pagedResult = employeeRepository.findAll(paging);

        return pagedResult.;
	}
}
