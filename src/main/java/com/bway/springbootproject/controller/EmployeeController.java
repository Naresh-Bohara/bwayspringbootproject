package com.bway.springbootproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bway.springbootproject.model.Employee;
import com.bway.springbootproject.service.DepartmentService;
import com.bway.springbootproject.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService empService;
	@Autowired
	private DepartmentService deptService;
	
	@GetMapping("/employee")
	public String getEmployee(Model model) {
		model.addAttribute("dlist", deptService.getAllDepartments());
		return "employee-form";
	}
	@PostMapping("/employee")
	public String postEmployee(@ModelAttribute Employee emp) {
		empService.addEmp(emp);
		return "redirect:/employee";
	}
	@GetMapping("/employee-list")
	public String getAll(Model model) {
		model.addAttribute("empList", empService.getAllEmps());
		return "employee-list";
	}
	@GetMapping("emp/edit")
	public String editEmployee(@RequestParam("id") long id, Model model) {
		model.addAttribute("eModel", empService.getEmpById(id));
		model.addAttribute("dlist", deptService.getAllDepartments());
		return "edit-employee";
	} 
	
	@PostMapping("emp/update")
	public String updateEmmployee(@ModelAttribute Employee emp) {
		empService.updateEmp(emp);
		return "redirect:/employee-list";
	}
	@GetMapping("emp/delete")
	public String deleteEmmployee(@RequestParam("id") long id) {
		empService.deleteEmp(id);
		return "redirect:/employee-list";
	}
	
	@GetMapping("emp/view")
	public String viewEmployee(@RequestParam("id") long id, Model model) {

	    model.addAttribute("eModel", empService.getEmpById(id));
 
	    return "view-employee";
	}
}
