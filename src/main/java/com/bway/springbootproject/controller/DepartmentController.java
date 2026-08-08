package com.bway.springbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springbootproject.model.Department;
import com.bway.springbootproject.service.DepartmentService;

@Controller
public class DepartmentController {
	@Autowired
	private DepartmentService deptService;
	
	@GetMapping("/department")
	public String getDepartmentForm() {
		
		return "department-form";
	}
	
	@PostMapping("/department")
	public String addDepartment(@ModelAttribute Department dept) {
		deptService.addDepartment(dept);
		return "department-form";
	}
	
	@GetMapping("department-list")
	public String getDepartments(Model model) {
		model.addAttribute("dlist", deptService.getAllDepartments());
		return "department-list";
	}
	
	@GetMapping("/dept/delete")
	public String deleteDepartment(@RequestParam("id") int id) {
		deptService.deleteDepartment(id);
		return "redirect:/department-list";
	}
	
	@GetMapping("dept/edit")
	public String editDepartment(@RequestParam("id") int id, Model model) {
		model.addAttribute("deptObject",deptService.getDepartmentById(id));
		return "edit-department";
	}
	
	@PostMapping("/dept/update")
	public String updateDept(@ModelAttribute Department dept) {
		deptService.updateDepartment(dept);
		return "redirect:/department-list";
	}
	
}
