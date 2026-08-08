package com.bway.springbootproject.service;

import java.util.List;

import com.bway.springbootproject.model.Employee;

public interface EmployeeService {
	void addEmp(Employee emp);
	void deleteEmp(long id);
	void updateEmp(Employee emp);
	Employee getEmpById(long id);
	List<Employee> getAllEmps();
}
