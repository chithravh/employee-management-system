
package com.example.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ems.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	Page<Employee> findByNameContainingIgnoreCase(
            String name, Pageable pageable);
	
	boolean existsByEmailIgnoreCase(String email);

}
