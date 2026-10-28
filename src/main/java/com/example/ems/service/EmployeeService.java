
package com.example.ems.service;
import com.example.ems.exception.DuplicateResourceException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ems.dto.EmployeeRequest;
import com.example.ems.dto.EmployeeResponse;
import com.example.ems.entity.Employee;
import com.example.ems.exception.ResourceNotFoundException;
import com.example.ems.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

	private final EmployeeRepository employeeRepository;

	// Create employee
	public EmployeeResponse createEmployee(EmployeeRequest request) {
		// Check whether the email already exists
		if (employeeRepository.existsByEmailIgnoreCase(request.getEmail())) {
		    throw new DuplicateResourceException("Email is already registered");
		}
		Employee employee = new Employee();
		employee.setName(request.getName());
		employee.setEmail(request.getEmail());
		employee.setDepartment(request.getDepartment());
		employee.setSalary(request.getSalary());

		Employee savedEmployee = employeeRepository.save(employee);

		return mapToResponseDTO(savedEmployee);
	}

	// Get all employees
	@Transactional(readOnly = true)
	public List<EmployeeResponse> getAllEmployees() {

		return employeeRepository.findAll().stream().map(this::mapToResponseDTO).toList();
	}

	// Get employee by ID
	@Transactional(readOnly = true)
	public EmployeeResponse getEmployeeById(Long id) {

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

		return mapToResponseDTO(employee);
	}


	 // Update employee
	public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {

	    Employee employee = employeeRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException(
	                    "Employee not found with id: " + id));

	    // Check whether another employee already uses this email
	    if (employeeRepository.existsByEmailIgnoreCase(request.getEmail())
	            && !employee.getEmail().equalsIgnoreCase(request.getEmail())) {
	        throw new DuplicateResourceException("Email is already registered");
	    }

	    employee.setName(request.getName());
	    employee.setEmail(request.getEmail());
	    employee.setDepartment(request.getDepartment());
	    employee.setSalary(request.getSalary());

	    Employee updatedEmployee = employeeRepository.save(employee);

	    return mapToResponseDTO(updatedEmployee);
	}

	// Delete employee
	public void deleteEmployee(Long id) {

		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

		employeeRepository.delete(employee);
	}

	// Convert Entity to Response DTO
	private EmployeeResponse mapToResponseDTO(Employee employee) {

		return new EmployeeResponse(employee.getId(), employee.getName(), employee.getEmail(),
				employee.getDepartment(), employee.getSalary());
	}

	public Page<EmployeeResponse> getEmployees(String search, Pageable pageable) {

		if (search == null || search.isBlank()) {
			return employeeRepository.findAll(pageable).map(this::mapToResponseDTO);
		}

		return employeeRepository.findByNameContainingIgnoreCase(search.trim(), pageable).map(this::mapToResponseDTO);
	}

	public List<EmployeeResponse> searchEmployees(String name) {
	    if (name == null || name.isBlank()) {
	        throw new IllegalArgumentException("Search name is required");
	    }

	    return employeeRepository
	            .findByNameContainingIgnoreCase(name.trim(), Pageable.unpaged())
	            .map(this::mapToResponseDTO)
	            .getContent();
	}

}
