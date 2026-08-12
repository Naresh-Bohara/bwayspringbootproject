package com.bway.springbootproject.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bway.springbootproject.model.Employee;
import com.bway.springbootproject.service.EmployeeService;

@RestController
public class EmployeeRestController {
	@Autowired
	private EmployeeService empService;
	@GetMapping("/api/emp/list")
	public List<Employee> getEmps() {
		return empService.getAllEmps();
	}
	
	@PostMapping("/api/emp/add")
	public String add(@RequestBody Employee emp) {
		
		empService.addEmp(emp);
		return "added success!";
	}
	
	@GetMapping("/api/emp/{id}")
	public Employee getOne(@PathVariable long id) {
		return empService.getEmpById(id);
	}
	
	@DeleteMapping("/api/emp/delete{id}")
	public String delete(@PathVariable long id) {
		empService.deleteEmp(id);
		return "delete success!";
	}
	@PutMapping("/api/empp/update{id}")
	public String update(@RequestBody Employee emp) {
		empService.updateEmp(emp);
		return "";
	}
	
	@GetMapping("/api/emp/j2o")
	public String jsonToObject() {
		RestTemplate rtmp = new RestTemplate();
		Employee emp = rtmp.getForObject("http://localhost:8080/api/emp/2", Employee.class);
		return "First Name: "+emp.getFname();
	}
	
	@GetMapping("/api/emp/ja2oa")
	public String jsonArrayToObjectArray() {
		RestTemplate rtmp = new RestTemplate();
		Employee[] emps= rtmp.getForObject("http://localhost:8080/api/emp/list", Employee[].class);
		return "Name: "+emps[2].getFname()+" "+emps[2].getLname();
	}
	
}
