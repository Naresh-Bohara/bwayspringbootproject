package com.bway.springbootproject.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bway.springbootproject.model.Department;
import com.bway.springbootproject.repository.DepartmentRepository;
import com.bway.springbootproject.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentRepository deptRepo;

	@Override
	public void addDepartment(Department dept) {
		deptRepo.save(dept);
	}

	@Override
	public void deleteDepartment(int id) {
		deptRepo.deleteById(id);
		
	}

	@Override
	public void updateDepartment(Department dept) {
		deptRepo.save(dept);
		
	}

	@Override
	public List<Department> getAllDepartments() {	
		return deptRepo.findAll();
	}

	@Override
	public Department getDepartmentById(int id) {
		return deptRepo.findById(id).get();
	}
	
}
