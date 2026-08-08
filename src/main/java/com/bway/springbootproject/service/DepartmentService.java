package com.bway.springbootproject.service;

import java.util.List;

import com.bway.springbootproject.model.Department;

public interface DepartmentService {
	void addDepartment(Department dept);
	void deleteDepartment(int id);
	void updateDepartment(Department dept);
	Department getDepartmentById(int id);
	List<Department> getAllDepartments();
}
