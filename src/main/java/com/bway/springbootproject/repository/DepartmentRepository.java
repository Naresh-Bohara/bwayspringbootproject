package com.bway.springbootproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bway.springbootproject.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	
}
