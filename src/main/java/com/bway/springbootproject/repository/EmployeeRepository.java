package com.bway.springbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bway.springbootproject.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	 
}
