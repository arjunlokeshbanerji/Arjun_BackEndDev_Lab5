package com.gl.employeeManagementSystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gl.employeeManagementSystem.entity.Employee;
import com.gl.employeeManagementSystem.repository.EmployeeRepository;
import com.gl.employeeManagementSystem.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		Employee savedEmployee =employeeRepository.save(employee);
		return savedEmployee;
	}

	@Override
	public Employee getEmployeeById(Long id) {
		Optional<Employee> emOptional = employeeRepository.findById(id);
		if(emOptional.isPresent()) {
			return emOptional.get();
		}
		throw new RuntimeException("Employee don't exist with id " + id.toString());
	}

	@Override
	public Employee updateEmployee(Long id, Employee employee) {
		Employee existingEmployee = getEmployeeById(id);
		existingEmployee.setEmail(employee.getEmail());
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		return employeeRepository.save(existingEmployee);
	}

	@Override
	public void deleteEmployeeById(Long id) {
		employeeRepository.deleteById(id);
		
	}

	

}
