
package com.example.ems.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ems.dto.ApiResponse;
import com.example.ems.dto.EmployeeRequest;
import com.example.ems.dto.EmployeeResponse;
import com.example.ems.dto.PaginationResponse;
import com.example.ems.dto.ResponseStatus;
import com.example.ems.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

	private final EmployeeService employeeService;

	// Create employee
	@PostMapping
	public ResponseEntity<ApiResponse<EmployeeResponse>> createEmployee(@Valid @RequestBody EmployeeRequest request) {

		EmployeeResponse response = employeeService.createEmployee(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(successResponse(response));
	}

	// Get all employees
	@GetMapping
	public ResponseEntity<ApiResponse<List<EmployeeResponse>>> getAllEmployees() {

		List<EmployeeResponse> employees = employeeService.getAllEmployees();

		return ResponseEntity.ok(successResponse(employees));
	}

	// Get employee by ID
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<EmployeeResponse>> getEmployeeById(@PathVariable Long id) {

		EmployeeResponse employee = employeeService.getEmployeeById(id);

		return ResponseEntity.ok(successResponse(employee));
	}

	// Update employee
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<EmployeeResponse>> updateEmployee(@PathVariable Long id,
			@Valid @RequestBody EmployeeRequest request) {

		EmployeeResponse employee = employeeService.updateEmployee(id, request);

		return ResponseEntity.ok(successResponse(employee));
	}

	// Delete employee
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteEmployee(@PathVariable Long id) {

		employeeService.deleteEmployee(id);

		ApiResponse<Void> response = new ApiResponse<>(new ResponseStatus("0", "Employee deleted successfully"), null);

		return ResponseEntity.ok(response);
	}

	// Common success response
	private <T> ApiResponse<T> successResponse(T body) {

		return new ApiResponse<>(new ResponseStatus("0", "Success"), body);
	}


	@GetMapping("/pagination")
	public ApiResponse<PaginationResponse<EmployeeResponse>> getEmployeesWithPagination(
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

		if (page < 0 || size < 1 || size > 100) {
			throw new IllegalArgumentException("Page must be 0 or greater, and size must be between 1 and 100");
		}

		Pageable pageable = PageRequest.of(page, size);

		Page<EmployeeResponse> result = employeeService.getEmployees("", pageable);

		PaginationResponse<EmployeeResponse> response = new PaginationResponse<>(result.getContent(),
				result.getTotalElements(), result.getTotalPages(), result.getNumber(), result.getSize());

		return successResponse(response);
	}

	@GetMapping("/search")
	public ApiResponse<List<EmployeeResponse>> searchEmployees(@RequestParam String name) {

		return successResponse(employeeService.searchEmployees(name));
	}

}
